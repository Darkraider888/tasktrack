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

TaskTrack also contains a public **Java Project Board** where students can showcase their deployed projects.

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
