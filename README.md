# EmboldenOccurences

To run:
```
javac Fig.java
java Fig
```

Sample output

```
##TEST 1 - aba, ababaaba
[0,4, 5,7]
<b>ababa</b><b>aba</b>

##TEST 2 - fig, configure figure
[3,5, 10,12]
con<b>fig</b>ure <b>fig</b>ure

##TEST 3 - fig, No occurrences here
No occurrences here

##TEST 4 - sid, sid
[0,2]
<b>sid</b>

##TEST 5 - sid, null
null

##TEST 6 - "", testing 123
testing 123

##TEST 7 - abaa, abaabaabcc
[0,6]
<b>abaabaa</b>bcc

##TEST 7 - abaa, abaaabaabcc
[0,3, 4,7]
<b>abaa</b><b>abaa</b>bcc
```
