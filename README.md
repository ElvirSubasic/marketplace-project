# marketplace-project
A description of the project and its requirements can be found in the document [Project Documentation](https://github.com/ElvirSubasic/marketplace-project/blob/master/Full-Stack%20Developer%20Interview%20-%20Task%201.docx)

## Steps for creating a testing environment
  - Setup **_MySql Server_** and create a database called **_marketplace_management_**
  - Open the **_marketplace-backend_** **_Spring Boot_** project
  - Configure database properites in `application.properties`, for example:
    
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/marketplace_management?useSSL=false
    spring.datasource.username=root
    spring.datasource.password=root
    ```
  - Run spring boot application
      - After application is run successfully, **_Hibernate_** will create all **_MySql_**_Tables and Columns
  - For seeding database with test data you can use **_CSV_** files from [CSV Import Files](https://github.com/ElvirSubasic/marketplace-project/tree/master/marketplace-database/CSV%20Import%20Files)
  - For testing REST API you can use the **_Postman_** collection from [Postman Collection JSON](https://github.com/ElvirSubasic/marketplace-project/blob/master/marketplace-postman/Marketplace-Java%20Spring%20Boot.postman_collection.json)
  - Open the **_marketplace-frontend_** **_Angular_** project
  - Run the **_Angular_** project
      - Check the port that is used by the **_Angular_** project
      - In the **_Spring Boot_** application controllers update the `@CrossOrigin` with your **_Angular_** port
   
        
