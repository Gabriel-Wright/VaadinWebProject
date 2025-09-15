-- H2 compatible schema + data

-- Tags
INSERT INTO TAG (ID, COLOR_HEX, TAGNAME, ICON) VALUES
  (151,'#ff0000','Development',NULL),
  (152,'#FFA500','Springboot',NULL),
  (251,'#A020F0','Game Jam',NULL);

-- Users
INSERT INTO USERS (ID, USERNAME, PASSWORD, ROLE) VALUES
  (1,'GMW','$2a$12$lOfOaW4b5ngvxdsKnQCy5umEgXWZW2/Is8Xmzsb5laX0sh7RRXpJa','ADMIN');

-- Visual Sources
INSERT INTO VISUAL_SOURCE (SOURCE_ID, FILE_NAME, IMAGE_PATH) VALUES
  (56,'webAppThumbnail.png','/static/img/webAppThumbnail.png'),
  (106,'bankstatementGitPage.PNG','/static/img/bankstatementGitPage.PNG'),
  (107,'contributions.png','/static/img/contributions.png'),
  (108,'excelPicture.PNG','/static/img/excelPicture.PNG'),
  (109,'verticallayoutvaadin.png','/static/img/verticallayoutvaadin.png'),
  (156,'thumbnailMaze.png','/static/img/thumbnailMaze.png'),
  (206,'chaoticCatsPreview.png','/static/img/chaoticCatsPreview.png'),
  (207,'mazeConcept.png','/static/img/mazeConcept.png'),
  (208,'ghostinthehall.png','/static/img/ghostinthehall.png'),
  (256,'mazeExample.png','/static/img/mazeExample.png'),
  (257,'scoringExample.png','/static/img/scoringExample.png'),
  (258,'waterPlayTest.png','/static/img/waterPlayTest.png'),
  (306,'mazeBlockBreakGif.gif','/static/img/mazeBlockBreakGif.gif'),
  (356,'waterSprites.png','/static/img/waterSprites.png'),
  (357,'mazeWaterFill.gif','/static/img/mazeWaterFill.gif'),
  (406,'javaGamePathFinding.gif','/static/img/javaGamePathFinding.gif'),
  (456,'javaThumbnail.png','/static/img/javaThumbnail.png'),
  (457,'javaSlopeDemo.gif','/static/img/javaSlopeDemo.gif'),
  (458,'spatial drawing.png','/static/img/spatial drawing.png'),
  (459,'gridExample.png','/static/img/gridExample.png'),
  (460,'gameplay preview gif.gif','/static/img/gameplay preview gif.gif'),
  (461,'milestones view.png','/static/img/milestones view.png');

-- Articles
INSERT INTO WEB_PAGE (ARTICLE_ACTIVE, THUMBNAIL, VERSION, ID, TIME_CREATED, TIME_LAST_UPDATED,
            ARTICLE_PREVIEW, ARTICLE_TEXT_PATH, TITLE) VALUES
  (true,56,1,201,'2024-04-26 17:47:21.126825','2024-04-26 17:47:21.126825',
   'An overview of the process it took to build this website.','/text/article01.txt','Full Stack Web Application Project using Springboot'),
  (true,156,1,301,'2024-04-29 00:16:11.029441','2024-04-29 00:16:11.029441',
   'Detailing the development of Perplexing Pawthways, a minigame within "Chaotic Cats".','/text/article02Maze.txt','Tile Based Maze Multiplayer Minigame'),
  (true,456,1,351,'2024-05-14 01:05:56.562163','2024-05-14 01:05:56.562163',
   'Explanation of the architecture behind my small arcade Java game.','/text/article03JavaGameDemo.txt','Simple 2D Game Engine in Java');

-- Tags
INSERT INTO WEBPAGE_TAG (TAG_ID, WEBPAGE_ID) VALUES
  (151,201),
  (152,201),
  (151,301),
  (251,301),
  (151,351);
