Installation guide
1. Download docker from https://www.docker.com/
2. Install the downloaded installer
    a. WSL must by version 2. Installer will require you to update
    b. check docker version in cmd (docker --version)

Selenium Grid
3. Create a structure with corresponding ports for Selenium Grid setup
E.g.
    Selenium Hub - Port 4445
    Selenium Node Chrome - Port 4446
    Selenium Node Firefox - Port 4447
    Selenium Node Opera - Port 4448

4. Download the images of the hub and nodes
    a. docker pull selenium/hub
    b. docker pull selenium/node-chrome-debug
    c. docker pull selenium/node-firefox-debug
    d. docker pull selenium/node-opera-debug
    e. docker pull selenium/node-edge               // No available debug yet for edge as of 09/17/2023

5. Setup up Docker Compose YAML 
    a. Search docker compose in https://github.com/SeleniumHQ/docker-selenium
    b. Use v2 of docker-compose.yml to expose the ports. https://github.com/SeleniumHQ/docker-selenium/blob/trunk/docker-compose-v2.yml

Automatic setup of Selenium Grid using Docker Compose 
6. Start Selenium Grid, run docker-compose up

Check Selenium Grid
7. Browse http://localhost:4444

8. Stop Selenium Grid and cleanup the created containers, run docker-compose down

9. Increase no. of Nodes 
    a. docker-compose up --scale chrome=2
    b. docker-compose up --scale firefox=2
    c. docker-compose up --scale chrome=2 --scale firefox=2
    e. docker compose -f "docker-compose-v2.yml" up --scale firefox=2 --scale chrome=2 -d (Sometimes chrome is only openning one node. Just enter again the line of codes)


10. Set up parallel execution by adding parallel="classes" in TestNG.xml


11. Download VNC viewer from https://www.realvnc.com/en/connect/download/viewer/
    a. Enter the IP address with port for the container you want to access
    b. E.g. 127.0.0.1:4446, then password is "secret"

Manual setup of Selenium Grid in Docker (https://www.youtube.com/watch?v=lgOiqS5AvSo)
5. Start/create a network
    a. docker network create SeleniumGridNetwork

6. Create and run the container then link them
    Hub
    a. docker run -d -p 4445:4444 --net SeleniumGridNetwork --name SeleniumHub selenium/hub
        i. Check by going to "localhost:4445"
    Node 1.1 - Chrome
    b. docker run -d -p 4446:5900 --net SeleniumGridNetwork -e HUB_HOST=SeleniumHub --name SeleniumChromeNode selenium/node-chrome-debug
    Node 1.2 - Chrome (docker to expose avail port)
    b. docker run -d -P --net SeleniumGridNetwork -e HUB_HOST=SeleniumHub --name SeleniumChromeNode2 selenium/node-chrome-debug
        i. Check by going to "localhost:4445", the node should be linked
    Node 2 - Firefox
    c. docker run -d -p 4447:5900 --net SeleniumGridNetwork -e HUB_HOST=SeleniumHub --name SeleniumFirefoxNode selenium/node-firefox-debug
    Node 3 - Opera
    d. docker run -d -p 4448:5900 --net SeleniumGridNetwork -e HUB_HOST=SeleniumHub --name SeleniumOperaNode selenium/node-opera-debug
    Node 4 - Edge
    d. docker run -d -p 4449:5900 --net SeleniumGridNetwork -e HUB_HOST=SeleniumHub --name SeleniumEdgeNode selenium/node-edge

7. Manually close the hub and nodes