````markdown
# ✅ TaskTrack

**TaskTrack** is a Java-based task management and student project showcase application built with **Spring Boot**, **MongoDB Atlas**, **Thymeleaf**, and **Bootstrap**.

The project demonstrates full CRUD operations, cloud database integration, responsive frontend development, Docker containerization, and deployment on Render.

🌐 **Live Application:**  
https://tasktrack-r7u7.onrender.com/

💻 **Repository:**  
https://github.com/Darkraider888/tasktrack

🔗 **LinkedIn Project Post:**  
https://lnkd.in/p/g5biha3M

---

## ✨ Features

### 📋 Task Management

- Create new tasks
- View all tasks
- Edit existing tasks
- Delete tasks
- Mark tasks as completed
- Set task priority
  - Low
  - Medium
  - High
- Set task status
  - Pending
  - In Progress
  - Completed

### 📊 Dashboard

- Total task count
- Pending task count
- In-progress task count
- Completed task count
- Live search
- Status filtering
- Priority filtering

### 🌐 Java Project Board

TaskTrack also includes a public **Java Project Board** where students can showcase their deployed Java projects.

Users can share:

- Student name
- Project name
- Live website URL
- GitHub repository
- LinkedIn project post

Project submissions can also be:

- Viewed
- Searched
- Edited
- Deleted

---

## 🛠️ Tech Stack

### Backend

- Java 25
- Spring Boot
- Spring MVC
- Spring Data MongoDB
- Maven

### Database

- MongoDB Atlas

### Frontend

- Thymeleaf
- HTML5
- CSS3
- Bootstrap 5
- Bootstrap Icons
- JavaScript

### Deployment

- Docker
- Render
- GitHub

---

## 🏗️ Project Structure

```text
src/main/java/com/mehedi/tasktrack/
│
├── controller/
│   ├── TaskController.java
│   └── ProjectSubmissionController.java
│
├── model/
│   ├── Task.java
│   └── ProjectSubmission.java
│
├── repository/
│   ├── TaskRepository.java
│   └── ProjectSubmissionRepository.java
│
└── TasktrackApplication.java

src/main/resources/
│
├── templates/
│   ├── index.html
│   ├── form.html
│   └── projects.html
│
└── application.properties
````

---

## 🔄 CRUD Operations

TaskTrack demonstrates the four main database operations:

| Operation | Feature               |
| --------- | --------------------- |
| Create    | Add a new task        |
| Read      | View stored tasks     |
| Update    | Edit task information |
| Delete    | Remove a task         |

The Java Project Board also supports creating, viewing, editing, and deleting project submissions.

---

## ☁️ MongoDB Atlas

The application stores data using **MongoDB Atlas**.

The main collections are:

```text
tasks
project_submissions
```

The MongoDB connection string is stored securely using an environment variable:

```properties
spring.mongodb.uri=${MONGODB_URI}
```

Database credentials are not stored in the GitHub repository.

---

## 🐳 Docker Deployment

The application is containerized using Docker.

The deployment process:

1. Build the Spring Boot project
2. Generate the executable JAR file
3. Create the production Docker container
4. Deploy the container to Render
5. Connect the application to MongoDB Atlas

---

## 🚀 Deployment

TaskTrack is deployed on **Render**.

### Live Application

[https://tasktrack-r7u7.onrender.com/](https://tasktrack-r7u7.onrender.com/)

### Main Dashboard

```text
/
```

### Java Project Board

```text
/projects
```

---

## 🤖 AI-Assisted Development

AI-assisted development was used throughout the project for:

* Project planning
* Spring Boot development
* Debugging
* UI improvements
* MongoDB configuration
* Docker configuration
* Deployment preparation

---

## 🎯 What I Learned

Through this project, I practiced:

* Java web application development
* Spring Boot MVC architecture
* MongoDB CRUD operations
* MongoDB Atlas integration
* Thymeleaf frontend development
* Java Streams and lambda expressions
* Form handling
* Search and filtering
* Environment variables
* Docker containerization
* Render cloud deployment
* GitHub project management

---

## 👨‍💻 Developer

**Mehedi**
CSE Undergraduate

GitHub:
[https://github.com/Darkraider888](https://github.com/Darkraider888)

---

## 🔗 Project Links

* 🌐 Live App: [https://tasktrack-r7u7.onrender.com/](https://tasktrack-r7u7.onrender.com/)
* 💻 GitHub Repository: [https://github.com/Darkraider888/tasktrack](https://github.com/Darkraider888/tasktrack)
* 🔗 LinkedIn Post: [https://lnkd.in/p/g5biha3M](https://lnkd.in/p/g5biha3M)

---

⭐ If you find the project useful, feel free to star the repository.

```
```
