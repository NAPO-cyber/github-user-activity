# Guthub Acitivty CLI
A very simple CLI app built with java to fetch github user's recent public activity using github API...

## Features
- fetches recent GitHub activity
- shows the activity in a user-friendly format
- handles invalid usernames
- Handles network/API errors
- Uses Java HttpClient
- Parses JSON using Gson

## Tech Stack
- Java
- Maven
- HttpClient
- Gson

## How to Run
1. Clone the repository
```bash
git clone https://github.com/<your-username>/github-activity.git
```

2. Navigate to the project
```bash
cd github-activity
```

3. Run
```bash
mvn compile
mvn exec:java -Dexec.mainClass="Activity" -Dexec.args="octocat"
```

## Example Output
```
- Pushed to octocat/Hello-World
- Starred spring-projects/spring-boot
- Created repository octocat/Test
```

## Project Structure
```
src/
 └── main/
     └── java/
         ├── Activity.java
         ├── Event.java
         └── Repo.java
```

## API Used
GitHub REST API - https://api.github.com/users/<username>/events
