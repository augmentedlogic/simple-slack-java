# simple-slack-java
Simple client to send a message to a slack channel


## Usage

```java

import com.augmentedlogic.simpleslack.SlackMessage;

```

```java      

try {

  SlackMessage slackMessage = new SlackMessage();
               slackMessage.setHook("https://hooks.slack.com/services/XXXXXXX/XXXXXXXX/XXXXXXX");
               slackMessage.setMessage("Hello World");
               slackMessage.setUsername("bot");           // optional, default: "bot"
               slackMessage.setChannel("general");        // optional, default: "general"
               slackMessage.setEmoji(":robot_face:");     // optional, default: ":robot_face:"
               slackMessage.send();

} catch(Exception e) {

  // do whatever you like

}

```

## via maven

```
<dependency>
 <groupId>com.augmentedlogic.simpleslack</groupId>
 <artifactId>simple-slack</artifactId>
 <version>0.6</version>
</dependency>
```
