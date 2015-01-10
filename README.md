# KuroNeko
A java-based web HTML parser and downloader

From the README file:

KuroNeko: a download manager for KissAnime
Written by Ethan Vovan

Ever wanted to download all the episodes of your favorite anime from KissAnime, but don't want to spend money on KPoints? KuroNeko solves that issue! All you need is the KissAnime link from your favorite anime, and KuroNeko does the rest work for you!

HOW TO USE:

1. Make sure you have the latest build of Java installed. If not, download here: http://www.java.com/
2. Run the .jar file, and then type your link into the first field.
3. Choose the place where you want the episodes to be downloaded. (NOTE: this will require a lot of hard drive space, so make sure you have enough, or an external hard drive!)
4. Click start, and let KuroNeko do the rest!

THINGS TO KNOW:
- KuroNeko only works with KissAnime!
- KuroNeko will download the latest episode first, and work its way down the list.
- KuroNeko will also only download the highest quality episodes of each anime. While we all enjoy the high quality, the episodes will also take up a lot of space on your computer.

HOW IT WORKS:

KuroNeko starts by downloading the root webpage of the anime from KissAnime, and then parses the file with Jsoup to find the links in the site. Then, it downloads all of those webpages and uses Jsoup to parse the links in the webpage. Once it finds and decodes the URL, KuroNeko will then download the episodes, one by one.

LICENSES:

External libraries used:
Jsoup v1.8.1 - Copyright © 2009 - 2014 Jonathan Hedley (jonathan@hedley.net)
Apache Commons I/O - Copyright Apache Software Foundation
Apache Commons Lang - Copyright Apache Software Foundation

Copyright (C) Ethan Vovan (theotakucube.weebly.com, lala56bob@gmail.com)

KuroNeko is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

KuroNeko is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

For other licenses of the external libraries used in KuroNeko, please see the /licenses folder in the root directory of KuroNeko.
