# AI-Powered Quiz Application

## Overview

This repository contains a comprehensive quiz application developed in Java using Swing for the graphical user interface. The application is designed to offer various modes of quiz interaction, profile management, scoring, and a persistent leaderboard. It is suitable for educational, competitive, and self-assessment environments.

## Key Features

- *Multiple Quiz Modes:*
  - *FreeRoam:* Attempt questions with no time limit.
  - *Challenger:* Complete all questions within an 8-minute window.
  - *Rapid Fire:* Solve each question within 15 seconds.

- *User Authentication:*
  - Supports user registration and login.
  - Displays profile page with previously saved credentials and quiz histories.
  - "Guest" mode available for anonymous play (with limited features).

- *Quiz Content:*
  - Mix of multiple-choice and fill-in-the-blank questions.
  - Randomization of questions to ensure unique quizzes each session.
  - 50-50 Lifeline available once per quiz attempt for MCQs.

- *Leaderboard:*
  - Displays the highest scores per username.
  - Accessible to all users, with no login required.

- *Sound and User Experience:*
  - Embedded background music for better engagement.
  - Modern, minimalistic interface for intuitive user experience.

- *Security & Data*
  - User credentials and scores stored locally with appropriate handling.
  - Sensitive information (e.g., API keys) is never stored in the repository.

## Technology Stack

- *Language:* Java SE 8 or newer
- *GUI Framework:* Java Swing
- *Data Persistence:* Local File I/O
- *Audio:* Java Sound API

## *Description of Main Files:*

- Main_Menu.java — Main landing and navigation window  
- Login.java — Authentication logic and forms  
- Profile.java — Displays user profile, credentials & match history  
- Quiz.java — Core quiz gameplay logic and UI  
- Leaderboard.java — Leaderboard table and display logic  
- Mode.java — Mode selection interface  
- Score.java — Score display and calculation  
- Rules.java — Quiz instructions and rules display  
- Session.java — Tracks session state and user info  
- assets/icons/ — Image and audio resources  
- game_history.txt — Quiz results and scores log  
- credentials.txt — User login info store (if applicable)

## Instructions for Use

1. *Clone the Repository:*
- git clone https://github.com/Yogesh0209/AI-Powered-Quiz-Game.git

2. *Open in NetBeans or other Java IDE:*
- Open the project as a Java project.
- Check that your JDK version is 8 or higher.

3. *Run the Application:*
- Execute Main_Menu.java to start.
- Register or log in to access full features, or use guest mode for a limited experience.

4. *Functionality Overview:*
- Use the Mode page to select quiz type.
- Complete quizzes to see instant results and leaderboard updates.
- Login enables profile storage and tracking.

## Notes for Reviewers

- The project demonstrates:
- Full object-oriented design and modular architecture.
- Persistent storage of user data, with sensitive data management best practices.
- Extensive use of Java Swing for GUI, including custom themes and controls.
- Handling of media (audio/music integration) and user interaction lifecycles.
- The codebase is suitable as a portfolio demonstration of both Java language proficiency and software engineering best practices.

## Screenshots

(Add screenshots of Main Menu, Quiz Mode, Profile Page, and Leaderboard here for quick visual reference)

## Future Improvements

- Database integration for scalable user data.
- REST API for quiz question fetching or multiplayer support.
- Web/app version using modern frameworks.
- More extensive admin dashboard for managing users and questions.

## License

This project is licensed for academic and demonstration use. For any deployment or production use, please contact the maintainer.

---

*Developed by:* Yogesh Singh  
*Contact:* [ybhadauriya40@gmail.com]

---
