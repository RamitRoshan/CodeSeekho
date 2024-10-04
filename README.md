## Code Seekho

Welcome to the **Code Seekho** repository! This Android application, built using Kotlin, is designed for users to practice coding through various challenges. 

## Features

- **Splash Screen**: A 3-second splash screen transitions to the login screen.
- **User Authentication**: 
  - Options to **Sign In**, **Sign Up**, and **Forgot Password**.
  - **Sign in with Google** for easy access.
- **Password Recovery**: When a user forgets their password, a reset link is sent to their registered email.
- **MVVM Architecture**: The app is structured using the Model-View-ViewModel (MVVM) pattern for better separation of concerns.
- **Data Binding**: Utilizes data binding for more efficient UI updates.
- **Retrofit**: Implemented for network operations to fetch coding challenges and submit solutions.
- **Database**: Questions and answers are stored using **XAMPP** and **phpMyAdmin**.

## Technologies Used

- Android
- Kotlin
- Firebase (for authentication and email services)
- MVVM Architecture
- Data Binding
- Retrofit

## API References
For database operations, the following APIs are used :

Fetch Questions API: http://localhost/quiz/api/fetch_questions.php <br>
Create Question API: http://localhost/quiz/api/create_question.php

## Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/RamitRoshan/CodeSeekho.git
