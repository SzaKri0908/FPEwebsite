--liquibase formatted sql
--changeset Bokros Olivér:insert_live_data

DELETE FROM events;
DELETE FROM news_page;
DELETE FROM gallery_images;

INSERT INTO events(title, text, image_url, event_date, deleted,facebook_event_link)
VALUES('Szemétszedés','A nyár folyamán sem csak pihentünk. Lelkes tagságunk egy kis része júliusban felkerekedett, hogy kicsit tisztábbá tegye a Mecsek környékét.👷‍♀️👷🌳
-----------------------------------------------------------------
A program a Fiatalok Pécsért Egyesület "Generation Now" programja keretében, a Nemzeti Együttműködési Alap pályázatának támogatásából valósult meg.
','C:/FPE/docker/gallery/szemetindex.png','2023-07-13', 0, 'https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl');

INSERT INTO events(title, text, image_url, event_date, deleted,facebook_event_link)
VALUES('Gyermekotthon', 'A nyár folyamán vendégül láttuk a Pécsi Gyermekotthon lakóit, akikkel egy VR szimulátor bemutatóval egybekötött közös játékos délutánt tartottunk.
-----------------------------------------------------------------
A program a Fiatalok Pécsért Egyesület "Generation Now" programja keretében, a Nemzeti Együttműködési Alap pályázatának támogatásából valósult meg.
','C:/FPE/docker/gallery/gyermekindex.png', '2023-07-12', 0 , 'https://www.facebook.com/fiatalokpecsert/posts/pfbid02rq9zfAREBGxc2vx6FmF8HZuGrFZ22gKLRaMMT1Prd5Mza6vQpcVDL2yURGh7e1oVl
');
INSERT INTO events(title, text, image_url, event_date, deleted,facebook_event_link)
VALUES('Airsoft', 'A "Hol a határ? – Egészség egy rohanó világban" programunk célja az volt, hogy felhívja a figyelmet az egészség fontosságára egy olyan világban, ahol a sietség és a stressz mindennapos kihívásokat jelent. Itt kiemelten fontosnak tartottuk, hogy szó essen a rendszeres sportról, és a szabad levegőn végzett testmozgásról.
A program keretében Zobák-aknára látogattunk, ahol a résztvevők betekintést nyerhettek és kipróbálhattak egy olyan sportot, amire a hétköznapokban valószínűleg nem sok lehetőségünk lenne. Az egész napos airsoft-játék során amellett, hogy igazán megmozgathatták magukat, a csapatokban végzett kihívások közepette a  kommunikációs és interperszonális készségeiket is fejleszthették a játékosok.
--------------------------------------------------------------------------
A program a Fiatalok Pécsért Egyesület "Generation Now" (NEAO-KP-1-2023/8-001407) projektje keretében, a Nemzeti Együttműködési Alap pályázatának támogatásával valósult meg.
','C:/FPE/docker/gallery/airsoftindex.png', '2023-07-13', 0, 'https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl');


INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Szemétszedés','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl', 0, 'C:/FPE/docker/gallery/szemetindex.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Szemétszedés','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl', 0, 'C:/FPE/docker/gallery/szemet2.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Szemétszedés','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl', 0, 'C:/FPE/docker/gallery/szemet3.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Szemétszedés','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl', 0, 'C:/FPE/docker/gallery/szemet4.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Szemétszedés','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl', 0, 'C:/FPE/docker/gallery/szemet5.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Szemétszedés','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl', 0, 'C:/FPE/docker/gallery/szemet1.png');


INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Gyermekotthon','https://www.facebook.com/fiatalokpecsert/posts/pfbid02rq9zfAREBGxc2vx6FmF8HZuGrFZ22gKLRaMMT1Prd5Mza6vQpcVDL2yURGh7e1oVl
',0,'C:/FPE/docker/gallery/gyermekindex.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Gyermekotthon','https://www.facebook.com/fiatalokpecsert/posts/pfbid02rq9zfAREBGxc2vx6FmF8HZuGrFZ22gKLRaMMT1Prd5Mza6vQpcVDL2yURGh7e1oVl
',0,'C:/FPE/docker/gallery/gyermek1.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Gyermekotthon','https://www.facebook.com/fiatalokpecsert/posts/pfbid02rq9zfAREBGxc2vx6FmF8HZuGrFZ22gKLRaMMT1Prd5Mza6vQpcVDL2yURGh7e1oVl
',0,'C:/FPE/docker/gallery/gyermek2.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Gyermekotthon','https://www.facebook.com/fiatalokpecsert/posts/pfbid02rq9zfAREBGxc2vx6FmF8HZuGrFZ22gKLRaMMT1Prd5Mza6vQpcVDL2yURGh7e1oVl
',0,'C:/FPE/docker/gallery/gyermek3.png');

INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Airsoft','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl',
       0,'C:/FPE/docker/gallery/airsoftindex.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Airsoft','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl',
       0,'C:/FPE/docker/gallery/airsoft1.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Airsoft','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl',
       0,'C:/FPE/docker/galleryy/airsoft2.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Airsoft','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl',
       0,'C:/FPE/docker/gallery/airsoft3.png');
INSERT INTO gallery_images(title, facebook_link, deleted, imgurl)
VALUES('Airsoft','https://www.facebook.com/fiatalokpecsert/posts/pfbid0vpuCSSs9cH3cd2GnrHqAXeLpp83rW82m4oA7FLZJmpiHofaUPiQadJ1abLTykyCkl',
       0,'C:/FPE/docker/gallery/airsoft4.png');