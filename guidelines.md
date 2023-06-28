# Guidelines
---
*All references are given for each chapter.*

## Programming Style

[This link](https://google.github.io/styleguide/javaguide.html) provides all information needed.

## Javadoc
```java
/**
 * Multiple lines of Javadoc text are written here,
 * wrapped normally...
 */
public int method(String p1) { ... }
```

and so

```java
/*
 * @(#)Blah.java        1.82 99/03/18
 *
 * Copyright (c) 1994-1999 Sun Microsystems, Inc.
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 */


package java.blah;

import java.blah.blahdy.BlahBlah;

/**
 *  
 * Class description goes here.
 *
 * @version      
 * 1.82 18 Mar 1999  * @author          
 * Firstname Lastname  
 */
public class Blah extends SomeClass {
  
```

This is just only an example.
Use comments if necessary.

## Repository Structure

All infomration needed can be found under [this link](https://medium.com/code-factory-berlin/github-repository-structure-best-practices-248e6effc405). 
We focus on the "**Basic Folder Structure**" and for the most text files (such as documentation, README.file, etc.) we're going to use markdown file (.md).

## Naming Convention (branches and commits)

Suggested branches naming convetion: `[task_number]-task_name`

Commit message naming convention: `` [task_number]-if-implemented-this-commit-will` ``

To get task numbers, use: [Trello Card Numbers Plus](https://chrome.google.com/webstore/detail/trello-card-numbers-plus/ncibjlmfhjcjnphnpphgphbflpdpliei/related)