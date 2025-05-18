# UrbanGrow – Urban Farming Management System

UrbanGrow is a Java Swing desktop application to help urban farmers manage garden plots, crops, plantings, harvests, and community tips.  
It uses a MySQL database for data storage.

## Tech Stack

- **Java (Swing):** Desktop GUI application framework  
- **MySQL:** Relational database for persistent storage  
- **JDBC (MySQL Connector/J):** Java Database Connectivity driver for MySQL  
- **Git:** Version control

## Features

- Manage Users, Garden Plots, Crops, Plantings, Harvests, and Tips
- Add, update, delete, and view records through an easy-to-use GUI
- All data is stored securely in a MySQL database

## Requirements

- Java 8 or higher
- MySQL 8.x (or compatible)
- MySQL Connector/J (JDBC driver)

## Setup

1. **Clone the repository**
2. **Create the MySQL database and tables**  
   - Use the provided SQL scripts or the schema in the documentation.
3. **Add your MySQL credentials**  
   - Copy `db.properties.example` to `db.properties` and fill in your details.
4. **Add MySQL Connector/J to your project’s classpath**
5. **Build and run the application**
   - Run `UrbanGrowApp.java` to start the GUI.

## Hiding Credentials

- Your real `db.properties` is ignored by Git.  
- Do **not** share your database password publicly.

## Made By - 

- Maneesh Kumar Reddy (1MS23IS154) 
- Atharv Dixit (1MS23IS155)
- Mihir Khaitan (1MS23IS156)

**Happy urban farming! 🌱**
