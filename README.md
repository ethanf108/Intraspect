<div align="center">

Intraspect
=====================

A framework for the general creation, manipulation, and verification(TM) of Java class files.

Intraspect was built and is currently being maintained by [Ethan Ferguson](https://github.com/ethanf108/) and [Shaun Thornton](https://github.com/homeworkhopper/)

</div>

## Overview

The Intraspect framework can be used to generate Java class files from scratch, without a compiler. It can also be used to restructure or otherwise manipulate existing class files without the need to recompile their sources. This can be particularly useful when it is necessary to change an aspect of an already compiled tool or library who's source code is not accessible. Such a change could range from the simple adjustment of an access modifier (private->public) to a complete overhaul of the functionality of an entire class or method.

## Scope

Composed of over 300 source files, Intraspect is composed of a complex hierarchy of classes representing constant pool entry types, attributes, and individual JVM instructions. Careful organization of these classes has been a focus from the very begining, and has allowed the project to remain manageable throughout development. Furthermore, a consistant use of the git version control system, along with branches here on GitHub, has greatly added to the overall organization of the project, while also leaving behind a comprehensive track record of its development.

## Limitations

As of its current state, the Intraspect framework is capable of reading any class file is existance. With that said, though, the process required to manipulate these class files is often quite lengthy and complex. Generating class files from scratch is a particularly headache-inducing task at the moment, and would likely benefit from some simplification at some point in the future. Luckily, plans are underway to help ease some complexity and remedy this issue.

## Future Development

This project has a couple branches actively in development, including a fully functional Command Line Interface [(cli branch)](https://github.com/ethanf108/Intraspect/tree/cli) and partly functional Graphical User Interface [(gui branch)](https://github.com/ethanf108/Intraspect/tree/gui), both of which were built upon the core Intraspect framework. These branches will be merged into master once reasonably stable. The goal of these additions is to provide a user-friendly way to manipulate class files without needing to directly deal with the underlying framework, thus avoiding the complexity associated with it.
