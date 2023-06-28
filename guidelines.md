# Guidelines
---

## Programming Style

For programming in Java we use [google style](https://google.github.io/styleguide/javaguide.html) 

## Javadoc
```java
/**
 * Multiple lines of Javadoc text are written here,
 * wrapped normally...
 */
public int method(String SomeName) { ... }
```

and so

```java
/*
 * @(#)foobar.java        1.82 99/03/18
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


package java.baz;

import java.baz.foo.bar;

/**
 *  
 * Class description goes here.
 *
 * @version      
 * 1.82 18 Mar 1999  * @author          
 * Firstname Lastname  
 */
public class foobar extends SomeClass {
  
```

This is just only an example.
Use comments if necessary.

## Repository Structure

We focus on the "**Basic Folder Structure**" and for the most text files (such as documentation, README.file, etc.) we are going to use markdown file (.md).
[Learn more](https://medium.com/code-factory-berlin/github-repository-structure-best-practices-248e6effc405). 

## Naming Convention (branches and commits)

Suggested branches naming convetion: `task_number-task_name`

Commit message naming convention: `task_number if implemented this commit will`

To get task numbers, use: [Trello Card Numbers Plus](https://chrome.google.com/webstore/detail/trello-card-numbers-plus/ncibjlmfhjcjnphnpphgphbflpdpliei/related)
