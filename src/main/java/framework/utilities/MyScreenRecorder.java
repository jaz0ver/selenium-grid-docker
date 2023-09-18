package framework.utilities;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.utilities.functions.CommonFunctions;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

public class MyScreenRecorder extends ScreenRecorder{

    static Logger Log = LoggerFactory.getLogger(MyScreenRecorder.class);
	public static ScreenRecorder screenRecorder;
	public String name;
	public static String reportFilePath, movieFileName;

	public MyScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
			Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
					throws IOException, AWTException {
		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
		this.name = name;
	}

	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {
		if (!movieFolder.exists()) {
			movieFolder.mkdirs();
		} else if (!movieFolder.isDirectory()) {
			throw new IOException("\"" + movieFolder + "\" is not a directory");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		movieFileName = name + "_" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat);
		return new File(movieFolder, movieFileName);

	}

	public static void startRecording(String methodName) {
		Log.info("Running Class: " + methodName);
		reportFilePath = CommonFunctions.getReportFilePath();
		File file = new File(reportFilePath);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
				getDefaultScreenDevice()
				.getDefaultConfiguration();

		try {
			screenRecorder = new MyScreenRecorder(gc, captureSize,
					new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
							Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
					null, file, methodName);
			Log.info("Start screen recording");
			screenRecorder.start();
		} catch (IOException e) {
			Log.error(e.toString());
			e.printStackTrace();
		} catch (AWTException e) {
			Log.error(e.toString());
			e.printStackTrace();
		}
	}

	public static void stopRecording() {
		try {
			if (screenRecorder != null) {
				Log.info("Stop screen recording. Path: " + reportFilePath + movieFileName);
            	screenRecorder.stop();
			}
        } catch (IOException e) {
            Log.error(e.toString());
            e.printStackTrace();
        }
	}
}
