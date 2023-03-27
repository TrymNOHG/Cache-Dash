# Getting Started

## Alternative 1 - Docker Desktop

1. Download and install Docker Desktop
2. Start the Docker engine by opening Docker Desktop
3. Navigate to the root folder of the application in a terminal named `idatt2105`
4. Run the command `docker-compose build`
5. To start the application, run the command `docker-compose up`
6. The website will run on `localhost:5173`
7. To stop the application, run the command `docker-compose stop`

---

## Alternative 2 - Start Application Manually

### MacOS/Linux:

1. Navigate to the root folder of the application in a terminal
2. To start the backend, run the following commands:
    1. `cd backend`
    2. `./mvnw clean install`
    3. `./mvnw spring-boot:run`
3. From the root folder of the application, run the following commands to start the frontend:
    1. `cd frontend`
    2. `npm install`
    3. `npm run dev`
4. The website will run on `localhost:5173`

### Windows:

1. Navigate to the root folder of the application in a command prompt
2. To start the backend, run the following commands:
    1. `cd backend`
    2. `./mvnw.cmd clean install`
    3. `./mvnw.cmd spring-boot:run`
3. From the root folder of the application, run the following commands to start the frontend:
    1. `cd frontend`
    2. `npm install`
    3. `npm run dev`
4. The website will run on `localhost:5173`

---

Congratulations! You've successfully set up and started the application.
