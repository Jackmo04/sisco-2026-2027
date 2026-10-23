Concurrent Systems a.y. 2026-2027 - ISI LM UNIBO - Cesena Campus

# Lab Activity #06 - 20261023

v1.1.0-20261022

### Implementing Critical Sections in Java

- Using `synchronized` blocks: `sisco.cs_raw`
- Using locks: `sisco.cs_withlocks`
    - `Lock` interface and `ReentrantLock` class, part of Java lib for concurrency

### Thread Safety in Java 

- [Lab Notes] Thread Safety
- **Work-in-Lab #01** -  Thread safety - lost update
	- Check lost update problem in `sisco.lost_updates`
	- Implement a solution 
- **Work-in-Lab #02** -  Thread safety - check and act
	- Check check & act problem in `sisco.check_act`
	- Implement a solution

### Model Checking with Java Path Finder (JPF) - Getting Started

- [About JPF](https://github.com/javapathfinder/jpf-core)
	- [Main docs](https://github.com/javapathfinder/jpf-core/wiki)
	- [NASA web site](https://ti.arc.nasa.gov/tech/rse/vandv/jpf/)
	- [Old JPF sourceforge web site](http://javapathfinder.sourceforge.net/)
- JPF Design and Details
	- [Relationship between JPF and JVM](https://github.com/javapathfinder/jpf-core/wiki/Model-Java-Interface)
	- [Understanding JPF output](https://github.com/javapathfinder/jpf-core/wiki/Understanding-JPF-output)
	- [Top-level design](https://github.com/javapathfinder/jpf-core/wiki/Search-Strategies)
	- [A full perspective about using JPF](https://github.com/javapathfinder/jpf-core/wiki/Different-applications-of-JPF)

- [Installing/Configuring JPF](https://github.com/javapathfinder/jpf-core/wiki/How-to-install-JPF) 
  - Using Docker - Steps:
    1) Open a terminal, choose a directory where work with JPF and clone the JPF repo:

       `git clone https://github.com/javapathfinder/jpf-core.git`
    2) Change directory to `jpf-core`

    3) Build the image:

       `docker-compose build`

    4) Start a container, mounting also a local directory to be used to host the Java projects to be model-checked. In the following the directory is mounted as `/home/sisco-jpf` inside the container:

       `docker-compose run --rm -v <your local directory>:/home/sisco-jpf jpf-dev` 

       For instance: mounting the `/sisco-jpf` directory included in the repo (in `lab-activity-06`):

       `docker-compose run --rm -v <path to SISCO repo>/sisco-2026-2027/lab-activities/lab-activity-06/sisco-jpf:/home/sisco-jpf jpf-dev` 

    5) Build JPF

        `./gradlew clean build`

       - On Windows systems you may get an error such as:
         
         `/bin/sh^M: bad interpreter`
          
         This is due to unwanted (not visible) line ending characters (`\r\n`) that could have been added to the file name `gradlew`. If a file with Windows line endings is used on Linux/macOS (inside the container), it causes the error mentioned above. 

           To remove the unwanted characters, just type (from inside the container):

         `sed -i 's/\r$//' gradlew`  
         `chmod +x gradlew`
 
          - `sed` is a command-line tool used to search, replace, insert, and delete text in files. Int his case it substitutes sequence of characters `\r\n` (represented by the pattern `\r$` with empty (so it removes the characters). 
          - `chmode +x` is to make the script file executable.

    6) To check if JPF is working, we launch one of the example available with the JPF distribution, the Racer (a Java program with a race condition):

        `java -jar build/RunJPF.jar src/examples/Racer.jpf`

    7) To run course examples, change dir to `/home/sisco-jpf`:

        `cd /home/sisco-jpf`

        and run JPF specifying the *.jpf file:

        `java -jar /home/jpf-core/build/RunJPF.jar src/main/java/sisco/jpf/TestSequential.jpf`  

        `java -jar /home/jpf-core/build/RunJPF.jar src/main/java/sisco/jpf/TestScenarios.jpf`  


  - Using VS Code and dev container [*]
    
    - the `sisco-jpf` folder in the repo includes a `.devcointainer` folder, that configures and exploits a containe to work with JPF from inside Visual Studio (VS) code.

    - By opening the folder with VS Code, an environment is automatically configured, preparing and running a docker container to run JPF. The first time, a docker image is built, using the `Dockerfile` available inside the folder.

    - Then, by opening a terminal, you will be inside the cointainer and you can launch the model checker by using the alias `jpf` which stands for `java -jar /opt/jpf/jpf-core/build/RunJPF.jar`. Example:

    ` jpf src/main/java/sisco/jpf/TestScenarios.jpf`  

 	
[*] Option suggested and prepared by F. Diotallevi (thanks!)