


Alternative 1 - Docker desktop

1) Download Docker desktop

2) Start the docker engine by opening docker desktop
   
3) Navigate to root folder of application in terminal: Named idatt2105
   
4) Input command "docker-compose build"
   
5) To start application use command "docker-compose up"

6) Website wil run on localhost:5173
   
7) To stop application use command "docker-compose stop"
<hr>

Alternative 2 - Start application manually

1) MacOS/Linux:
   1) Navigate to root folder of application in terminal
   2) Input following commands to start backend
      1) cd backend
      2) ./mvnw clean install
      3) ./mvnw spring-boot:run
   3) From application root input following commands to start frontend
      1) cd frontend
      2) npm install
      3) npm run dev
   4) Website wil run on localhost:5173
  

2) Windows
   1) Navigate to root folder of application in command prompt
   2) Input following commands to start backend
      1) cd backend
      2) ./mvnw.cmd clean install
      3) -/mvnw.cmd spring-boot:run
   3) From application root input following commands to start frontend
      1) cd frontend
      2) npm install
      3) npm run dev
   4) Website wil run on localhost:5173


