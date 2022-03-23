<div align="center">

Intraspect
=====================

A framework for the general creation, manipulation, and verification(TM) of Java class files.

Intraspect was built and is currently being maintained by [Ethan Ferguson](https://github.com/ethanf108/) and [Shaun Thornton](https://github.com/homeworkhopper/)

</div>

## Overview

The Intraspect framework can be used to generate Java class files from scratch, without a compiler. It can also be used to restructure or otherwise manipulate existing class files without the need to recompile their sources. This can be particularly useful when it is necessary to change the functionality of an already compiled tool or library who's source code is not accessible. With Intraspect, such a task would be possible.

## Limitations

As of its current state, the Intraspect framework is capable of reading any class file is existance. With that said, though, the process required to manipulate these class files is often quite lengthy and complex. Generating class files from scratch is a particularly headache-inducing task at the moment, and would likely benefit from some simplification at some point in the future. Luckily, plans are underway to help ease some complexity and remedy this issue.

## Future Development

This project has a couple branches actively in development, including a fully functional Command Line Interface and partly functional Graphical User Iterface, both of which were built upon the core Intraspect framework.
