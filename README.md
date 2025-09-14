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

## Directory Structure
