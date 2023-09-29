    # Pull docker-selenium
    docker pull elgalu/selenium
    
    # Pull Zalenium
    docker pull dosel/zalenium
    
    # Run it!
    docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos \
      --privileged dosel/zalenium start
      
    # Point your tests to http://localhost:4444/wd/hub and run them

    # Stop
    docker stop zalenium

    Grid console:
    http://localhost:4444/grid/console

    Dashboard:
    http://localhost:4444/dashboard/

    Node Live view:
    http://localhost:4444/grid/admin/live?ref=hackernoon.com


    NOTE: REMOVE ALL OTHER IMAGES BEFORE RUNNING ZALENIUM 

    E.g.
    docker run --rm -ti --name zalenium -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start



    C:\Users\jazxz\Documents\SPI\SPI - ZN\Employment - ZN\Automation\Selenium Grid 2023 VSC - GIT\selenium-grid-docker\src\main\resources\zalenium\videos