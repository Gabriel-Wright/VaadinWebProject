INSERT INTO "TAG" (ID, TAGNAME, COLOR_HEX, ICON) VALUES (1, 'Gaming', '#ff0000', 'GAMEPAD');
INSERT INTO "TAG" (ID, TAGNAME, COLOR_HEX, ICON) VALUES (2, 'Travel', '#edd000', 'FLIGHT_TAKEOFF');
INSERT INTO "TAG" (ID, TAGNAME, COLOR_HEX, ICON) VALUES (3, 'Art', '#3deeed', 'PAINTBRUSH');
-- Insert sample tags
INSERT INTO "TAG" (ID, TAGNAME, COLOR_HEX, ICON) VALUES (-1, 'Tag1', '#ff0000', 'EYE');
INSERT INTO "TAG" (ID, TAGNAME, COLOR_HEX, ICON) VALUES (-2, 'Tag2', '#ff0000', 'EYE');
INSERT INTO "TAG" (ID, TAGNAME, COLOR_HEX, ICON) VALUES (-3, 'Tag3', '#ff0000', 'EYE');

-- Insert sample article formats
INSERT INTO "ARTICLE_FORMAT" (ARTICLE_FORMAT_ID, NAME) VALUES (-1, 'Format A');
INSERT INTO "ARTICLE_FORMAT" (ARTICLE_FORMAT_ID, NAME) VALUES (-2, 'Format B');

-- Insert sample web pages
INSERT INTO "WEB_PAGE" (ID, VERSION, TITLE, ARTICLE_FORMAT_ID) VALUES (-1000, 0, 'Memories Of Murder: Analysis and Ending explained', -1);
INSERT INTO "WEB_PAGE" (ID, VERSION, TITLE, ARTICLE_FORMAT_ID) VALUES (-1001, 0, 'JFrame Game Development', -1);
INSERT INTO "WEB_PAGE" (ID, VERSION, TITLE, ARTICLE_FORMAT_ID) VALUES (-1002, 0, 'A.I Pathfinding approaches', -1);
--INSERT INTO "WEB_PAGE" (ID, VERSION, TITLE, ARTICLE_FORMAT_ID) VALUES (-1001, 0, 'Web Page 2', -1);
--INSERT INTO "WEB_PAGE" (ID, VERSION, TITLE, ARTICLE_FORMAT_ID) VALUES (-1002, 0, 'Web Page 3', -2);

-- Insert sample dates for the web pages
UPDATE "WEB_PAGE" SET TIME_CREATED = '2023-01-01T10:00:00', TIME_LAST_UPDATED = '2023-01-01T10:00:00' WHERE id = -1000;
UPDATE "WEB_PAGE" SET TIME_CREATED = '2024-08-03T18:49:00', TIME_LAST_UPDATED = '2024-08-03T18:49:00' where id = -1001;
UPDATE "WEB_PAGE" SET TIME_CREATED = '2024-02-15T15:23:00', TIME_LAST_UPDATED = '2024-02-15T15:23:00' where id = -1002;
--UPDATE "WEB_PAGE" SET TIME_CREATED = '2023-01-02T10:00:00', TIME_LAST_UPDATED = '2023-01-02T10:00:00' WHERE id = -1001;
--UPDATE "WEB_PAGE" SET TIME_CREATED = '2023-01-03T10:00:00', TIME_LAST_UPDATED = '2023-01-03T10:00:00' WHERE id = -1002;

-- Insert sample tags for the web pages
INSERT INTO "WEBPAGE_TAG" (WEBPAGE_ID, TAG_ID) VALUES (-1000, -1);
INSERT INTO "WEBPAGE_TAG" (WEBPAGE_ID, TAG_ID) VALUES (-1000, -2);
INSERT INTO "WEBPAGE_TAG" (WEBPAGE_ID, TAG_ID) VALUES (-1001, 1);
INSERT INTO "WEBPAGE_TAG" (WEBPAGE_ID, TAG_ID) VALUES (-1002, 1);
INSERT INTO "WEBPAGE_TAG" (WEBPAGE_ID, TAG_ID) VALUES (-1002, 3);
--INSERT INTO "WEBPAGE_TAG" (WEBPAGE_ID, TAG_ID) VALUES (-1001, -1);
--INSERT INTO "WEBPAGE_TAG" (WEBPAGE_ID, TAG_ID) VALUES (-1002, -3);

-- Insert thumbnail

UPDATE "WEB_PAGE" SET THUMBNAIL = 'img/thumbnails/memoriesOfMurderThumbnail.jpg' WHERE id = -1000;
UPDATE "WEB_PAGE" SET THUMBNAIL = 'img/thumbnails/gameTest.png' WHERE id = -1001;
UPDATE "WEB_PAGE" SET THUMBNAIL = 'img/thumbnails/wiiParty.png' where id = -1002;

--Insert Text of articles

UPDATE "WEB_PAGE" SET ARTICLE_TEXT = '<v,1>Hello welcome to this article brahHello welcome to this article [p] <h,>brahHello welcome to this article brahHello welcome to this article brah[p] <h,>Hello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brah' WHERE id = -1000;
UPDATE "WEB_PAGE" SET ARTICLE_TEXT = '<v,0>Hello welcome to this article brahHello welcome t[p]<,>o this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brah' WHERE id = -1001;
UPDATE "WEB_PAGE" SET ARTICLE_TEXT = 'Hello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brahHello welcome to this article brah' WHERE id = -1002;

INSERT INTO "VISUAL_SOURCE" (SOURCE_ID, IMAGE_PATH) VALUES (0,'img/thumbnails/memoriesOfMurderThumbnail.jpg');
INSERT INTO "VISUAL_SOURCE" (SOURCE_ID, IMAGE_PATH) VALUES (1, 'img/thumbnails/gameTest.png');