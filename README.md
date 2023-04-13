
<p align="center">
<img src="./README%20Assets/KaizenLogoDS.png" width="50%">
</p>

<p align="center">
by <a href="https://github.com/narlock">narlock</a>
</p>

<!-- GitHub Shields-->
<p align="center">
  <a href="https://github.com/narlock/Kaizen/releases/"><img src="https://img.shields.io/github/downloads/narlock/Kaizen/total.svg"></a>
  <a href="https://github.com/narlock/Kaizen/releases/"><img src="https://img.shields.io/github/v/release/narlock/Kaizen"></a>
  <a href="https://github.com/narlock/Kaizen/commits/main"><img src="https://img.shields.io/github/last-commit/narlock/Kaizen"></a>
  <a href="https://discord.gg/eEbEYbXaNS"><img src="https://discordapp.com/api/guilds/821757961830793236/widget.png?style=shield"></a>
</p>

<!-- Social Links -->
<p align="center">
  <a href="https://youtube.com/narlock" style="padding:10px;"><img src="https://i.imgur.com/5npSWBq.png" alt="YouTube"></a>
  <a href="https://instagram.com/narlockdev" style="padding:10px;"><img src="https://i.imgur.com/DCFiEHr.png" alt="Instagram"></a>
  <a href="https://patreon.com/narlock" style="padding:10px;"><img src="https://i.imgur.com/iXAguWQ.png" alt="Patreon"></a>
  <a href="https://twitter.com/narlockDev" style="padding:10px;"><img src="https://i.imgur.com/W8iSkd5.png"></a>
<p>

<hr>

## Your Intentional and Minimalist Productivity and Self-Development Hub.

**Kaizen**, also referred to in some places as OpenLife or Open Source Life Application (OSLA) started as a Node.js web server application that utilizes a MySQL database to manage data. This implementation is now called **KaizenWeb**. Beginning in 2023, a Java desktop application began development called **KaizenClient**, which implements similar features in a local Java package. Depending on which application is downloaded, there are different requirements needed.

- [KaizenClient](#kaizenclient)
  - [Features](#features)
  - [Setup KaizenClient](#setup-kaizenclient)
- [KaizenWeb](#kaizenweb)
  - [Features](#features-1)
  - [Setup KaizenWeb](#setup-kaizenweb)
- [Original Concept Idea](#original-concept-idea)

# **KaizenClient**
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)

## **Features**

### **Todo**
- Simple to-do task organizer. Get work done by laying out your to-do list! Working on multiple projects? Create an epic to store different tasks by epic. Tasks associated with an epic will appear with that epic's assigned color.

<p align="center">
  <img src="./README%20Assets/Todo.png" width="70%"/>
</p>

- A task is marked completed by clicking the box on the right-hand side of the task. The task will be moved to the completed section.
- Create tasks and epics using the respective '+' button!
- Update tasks and epics using the pencil button!
- Delete epics using the respective 'X' button!
- Tasks that are marked as complete can be viewed by clicking 'Completed Items'. This will display all of the tasks that the user has completed. The user can choose to delete all of the completed tasks with the 'X' button.
- 'Todo Items' will display the tasks that have not been completed.
- 'View All Items' will display all of the tasks, including epic-unassigned and epic-assigned.
- By selecting the text of an epic, you can view all of the tasks that correspond to that epic.
- Sort your tasks by date and priority. (Select them again to reverse the sort order!)
- Share your Todo list by selecting the copy to clipboard button!

### **Habits**
- Keep track of habits that you want to build by accumulating a streak representing the amount of days you have completed your new habit!

<p align="center">
  <img src="./README%20Assets/Habits.png" width="70%"/>
</p>

- Start habits by selecting the 'Start Habit' tab under 'Habits'.
- Update and delete habits by selecting the 'Update Habits' tab under 'Habits'.
- Streaks are given to habits that occur everyday or once a week! (Marked with the fire emoji)
- Amount of completions are given to habits that do not occur everyday or once a week. (Marked with the star emoji)
- The longer the streak you achieve for a habit, the higher level it will gain. Level is based off of the color of the habit:
  - Level 1 (GREEN) - 0 to 7 days
  - Level 2 (GOLD) - 8 to 30 days
  - Level 3 (DIAMOND) - 31 to 90 days
  - Level 4 (PINK) - 91 to 300 days
  - Level 5 (RED) - 301+ days
- Share your habits by selecting the copy to clipboard button!

### **AntiHabits**
- Trying to break bad habits too? Kaizen has you covered.

<p align="center">
  <img src="./README%20Assets/AntiHabits.png" width="70%"/>
</p>

- Begin to break a habit by selecting the '+'. Note: dates are marked in the format yyyy-MM-dd (year-month digits-day digits). All dates entered are required to follow this format. For example, April 2nd, 2023 will be typed as 2023-04-02
- If you relapse on a habit, you can press the counter-clockwise button to reset your days since counter.
- You can delete an Anti Habit by pressing the 'X' corresponding to that Anti Habit.
- Similarly to habits, the longer the streak you achieve for an anti habit, the higher level it will gain. Level is based off of the color of the habit:
  - Level 1 (GREEN) - 0 to 7 days
  - Level 2 (GOLD) - 8 to 30 days
  - Level 3 (DIAMOND) - 31 to 90 days
  - Level 4 (PINK) - 91 to 300 days
  - Level 5 (RED) - 301+ days

### **Journal**
- Make journaling a habit by using the Kaizen journal. Kaizen's journal offers simple prompts to answer every day.

<p align="center">
  <img src="./README%20Assets/Journal.png" width="70%"/>
</p>

- Save your journal entry by pressing the 'Save Entry' button! Note: you *must* hit 'Save Entry' for your journal entries to save.
- View previous journal entries by pressing the 'Previous Entry' button!
- To get back to the present journal entries, press the 'Following Entry' button.
- Toggle 'How was your day' question in Settings.
- 2/4 Prompt Modes
- Customizable Prompt Headers.

### **Home Widgets**
- Have all of your productivity tools in one place.

<p align="center">
  <img src="./README%20Assets/Home.png" width="70%"/>
</p>

- Change which widgets appear inside of the customization menu! 
- Simply select the 'Customize Home' menu option from 'Home', and utilize the combo boxes to select the widgets to suit your needs.

## **High-level Software Architectural Diagram**
<p align="center">
  <img src="./README%20Assets/HL_UML.png" width="70%"/>
</p>

## **Setup KaizenClient**
### Requirements

1. Supported Operating Systems

      Kaizen has been tested on the following operating systems:
      - Windows 10
      - Linux (Ubuntu 20.04 LTS)
      - Mac OS X (Ventura)

2. Java Runtime Environment

    KaizenClient was developed utilizing the Java programming language. An installation of the Java Runtime Environment is required to run Java applications. KaizenClient runs on **Java 8**. A download for Java can be found [here](https://www.java.com/en/download/).

3. Downloading Kaizen

    To download Kaizen, read through the download page [here](https://github.com/narlock/Kaizen/releases) and select the Kaizen JAR file to download from this page. This file contains the entire application and can be opened utilizing the Java Runtime Environment.

    > **Note**
    > Depending on your operating system, there may be certain permissions you must authorize Kaizen to in order to execute the program.
    - For Linux distributions, permissions to execute the application must be granted.
    - For macOS, depending on the installation, the user may need to launch the application through the command line (assuming the application already has permissions). To run through the command line, navigate the terminal to the directory of Kaizen, then type the command `java -jar Kaizen.jar`. The application will now function properly.
      - If you encounter the ability to not save, or view any files, please try launching using the command line.

# **KaizenWeb**
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![NodeJS](https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## **Features**
This feature description is currently a work in-progress.

## **Setup KaizenWeb**
This setup description is currently a work in-progress.

<hr>

## Original Concept Idea

I always found that creating some sort of consistency in my life to be somewhat of a challenge. Especially with forming habits like waking up early, making sure I'm drinking enough water, consuming a decent diet, achieving my study goals, spending enough time with friends & family... The list goes on. I have found that using multiple applications to try and build multiple different habits (i.e. a journal for journaling habit, a water reminder app to remind me to drink water, a to-do list app for my daily to-do) was a hassle. I thought it would be great to be able to pile all of those applications into a simple "Life Hub", meaning everything that I need to access is all in one place. No need to go from application to application - they're all in front of me.

<p align="center">Developed by <a href="https://github.com/narlock">narlock</a><br><i>KaizenWeb was created on August 6th, 2022.</i>
<br><i>Kaizen (Java) was created on January 7th, 2023 and first released on April 2nd, 2023.</i></p>
