# CoffeeClicker Project 

Welcome to the CoffeeClicker project!

This README provides all the necessary instructions to properly configure
and run the application.

-----------------------------------------------------------------------
PREREQUISITES
-----------------------------------------------------------------------

Before running the application, make sure you have the following installed:

- Java Development Kit (JDK)
- IntelliJ IDEA or any Java IDE of your choice
- MySQL
- Git (optional)

-----------------------------------------------------------------------
STEP 1: Install the Font
-----------------------------------------------------------------------

1. Go to the "res" folder inside the project directory.
2. Open the file named "Pixeled.ttf".
3. Click the "Download" or "Install" button to install the font on your system.

-----------------------------------------------------------------------
STEP 2: Include External Libraries
-----------------------------------------------------------------------

1. Open the "lib" folder inside the project directory.
2. Add all the libraries inside this folder to your project.

   - If using IntelliJ IDEA:
     Go to: File > Project Structure > Libraries > Add JARs or Directories,
     and include all the JAR files found in the "lib" folder.

-----------------------------------------------------------------------
STEP 3: Set Up the MySQL Database
-----------------------------------------------------------------------

1. Make sure MySQL is installed and running.
2. Open your terminal and type the following command:

   mysql -u root -p

3. After entering your MySQL password, execute the following:

   DROP DATABASE IF EXISTS cofeeclicker_schema;
   CREATE DATABASE cofeeclicker_schema;
   USE cofeeclicker_schema;
   SOURCE C:/DPO_P2_CoffeeClicker/sql/Dump20250514ultimaversion.sql;

   NOTE: Update the path to the .sql file if your location differs inside of the folder named Database 
         and swap the "/" for "\".

-----------------------------------------------------------------------
STEP 4: Configure Database Access
-----------------------------------------------------------------------

1. Open the "data" folder inside the project directory.
2. Open the file named "ConfigurationFile.json".
3. Locate the "password" field and replace its value with your actual
   MySQL database password.

-----------------------------------------------------------------------

You're all set! Once these steps are complete, you can run the project
from your IDE.

Thank you for using CoffeeClicker!
