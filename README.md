# Java Tabularize Application

> The Java Data Table Application is a Java Spring Boot project that provides a user-friendly web interface for managing and visualizing tabular data stored in JSON, CSV, and Excel file formats. Users can log in to the application, and upon authentication, they have the option to upload data files in these formats. Once the data is uploaded, it is parsed and displayed in a table format for easy viewing and analysis.


### Features
 >1. User Authentication: Secure user authentication system to ensure that only authorized users can access the application.
 >2. Data Upload: Users can upload data in JSON, CSV, or Excel formats using a simple and intuitive user interface.

 >3. Table View: The uploaded data is presented in a table format, making it easy to view, filter, and sort the information.

### Setup DB Details in /src/main/resources/application.properties
>1. Postgresql should be up and running in your local and add related config details in mentioned file to run application


```bash
  example 
  spring.datasource.url=jdbc:postgresql://localhost:5432/tabularizeapp
  spring.datasource.username=admin
  spring.datasource.password=admin
```

### Run Application
>1. after setup run the application from with main from TabularizeApplication.java 




