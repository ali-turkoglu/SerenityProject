#Serenity Project
Serenity BDD is an open source library that aims to make the idea of living documentation a reality.

Here is the [link](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github) for simple documentation.

###Step to Create Project
1. Create a maven project called `Serenity`
2. under `pom.xml`
   1. add below property section
      ```xml
      <properties>
           <maven.compiler.source>11</maven.compiler.source>
           <maven.compiler.target>11</maven.compiler.target>
       </properties>
      ```
   2. Add dependencies
      ```xml
       <!--        This is for base support for anything we do with serenity-->
           <dependency>
               <groupId>net.serenity-bdd</groupId>
               <artifactId>serenity-core</artifactId>
               <version>2.4.4</version>
           </dependency>
           <!--        this is the dependency that wrap up rest assured with additional serenity support-->
           <dependency>
               <groupId>net.serenity-bdd</groupId>
               <artifactId>serenity-rest-assured</artifactId>
               <version>2.4.4</version>
           </dependency>
           <!-- Junit 5 dependency -->
           <dependency>
               <groupId>org.junit.jupiter</groupId>
               <artifactId>junit-jupiter</artifactId>
               <version>5.7.1</version>
               <scope>test</scope>
           </dependency>

           <dependency>
               <groupId>io.github.fabianlinz</groupId>
               <artifactId>serenity-junit5</artifactId>
               <version>1.6.0</version>
           </dependency>

           <dependency>
               <groupId>org.slf4j</groupId>
               <artifactId>slf4j-simple</artifactId>
               <version>1.7.30</version>
           </dependency>

           <dependency>
               <groupId>com.github.javafaker</groupId>
               <artifactId>javafaker</artifactId>
               <version>1.0.2</version>
           </dependency>
      ```
   3. Add Build plugins
      ```xml
          <build>
           <plugins>
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-compiler-plugin</artifactId>
                   <version>3.8.1</version>
                   <configuration>
                       <source>8</source>
                       <target>8</target>
                   </configuration>
               </plugin>
               <!--            This is where the report is being generated after the test run -->
               <plugin>
                   <groupId>net.serenity-bdd.maven.plugins</groupId>
                   <artifactId>serenity-maven-plugin</artifactId>
                   <version>2.4.4</version>
                   <executions>
                       <execution>
                           <id>serenity-reports</id>
                           <phase>post-integration-test</phase>
                           <goals>
                               <goal>aggregate</goal>
                           </goals>
                       </execution>
                   </executions>
               </plugin>
               <!--         We want to run all the tests then generate one report -->
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-surefire-plugin</artifactId>
                   <version>3.0.0-M5</version>
                   <configuration>
                       <testFailureIgnore>true</testFailureIgnore>
                   </configuration>
               </plugin>
           </plugins>
       </build>
      ```
3.Create a package called `EU8` under `src/test/java`
   1. under EU8 create `spartan` and under spartan create `admin` package
   2. create a class called `SpartanAdminGetTest`
   
   