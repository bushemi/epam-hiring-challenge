https://gitlab.com/epam-dnipro-hiring-challenge/hiring-event-2019-1/coding-test/wikis/Password%20hash%20cracking

# Password hash cracking

Find the password of the following MD5 hash value: `69c459dd76c6198f72f0c20ddd3c9447` Use the brute-force approach and assume that the password consists of lowercase letters from the English alphabet. Run the calculation on multiple threads in order to get the result faster.

Codebase can be found below.

The application should run multiple threads to try out every possible combination. The threads should run in parallel to achieve the best performance on the CPUs and it must be ensured that every combination is only tried once. (If one thread already tried a combination, the other threads should not try it again). Once the password is found all threads must stop.

At the end the program should output the result and the time it took to crack the code.

To use the HashCalculator class, download the Guava library here:

Jar file: http://search.maven.org/remotecontent?filepath=com/google/guava/guava/21.0/guava-21.0.jar

Maven dependency: https://mvnrepository.com/artifact/com.google.guava/guava/21.0

```
package com.epam.training; 

import java.nio.charset.StandardCharsets; 
import com.google.common.hash.Hasher; 
import com.google.common.hash.Hashing; 

public class HashCalculator { 
    public String hash(String toHash) { 
        Hasher hasher = Hashing.md5().newHasher(); 
        hasher.putString(toHash, StandardCharsets.UTF_8); 
        return hasher.hash().toString(); 
    } 
}
```

More complicated task: 4fd0101ea3d0f5abbe296ef97f47afec