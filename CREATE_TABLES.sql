CREATE SEQUENCE seq_CookieClicker
    increment by 1
    start with 1;

CREATE TABLE COOKIECLICKERS(
   CookieClicker_id NUMBER,
   cookies_per_click NUMBER,
   CONSTRAINT PK_COOKIECLICKER PRIMARY KEY (CookieClicker_id)
);

INSERT INTO COOKIECLICKERS VALUES(78, 2);
CREATE SEQUENCE seq_PLAYER
INCREMENT BY 1
START WITH 1;

CREATE TABLE PLAYERS(
    Player_id NUMBER,
    Name VARCHAR2(100) UNIQUE NOT NULL,
    Cookies NUMBER,
    CookieClicker_id NUMBER,
    CONSTRAINT PK_PLAYER PRIMARY KEY (Player_id),
    CONSTRAINT FK_COOKIECLICKER FOREIGN KEY (CookieClicker_id) REFERENCES COOKIECLICKERS (CookieClicker_id)
);
INSERT INTO PLAYERS VALUES (78, 'alin', 3, 78);
SELECT * FROM PLAYERS;
SELECT * FROM COOKIECLICKERS;
CREATE SEQUENCE seq_achievement
increment by 1
start with 1;

CREATE TABLE ACHIEVEMENTS(
    Achievement_id NUMBER,
    Name VARCHAR2(100),
    Description VARCHAR2(1000),
    Achieved NUMBER(1),
    Player_id NUMBER,
    CONSTRAINT FK_PLAYER FOREIGN KEY (Player_id) REFERENCES PLAYERS (Player_id),
    CONSTRAINT PK_ACHIEVEMENT PRIMARY KEY (Achievement_id)
);
SELECT * FROM ACHIEVEMENTS;


CREATE SEQUENCE seq_COOKIEMAKER
increment by 1
start with 1;

CREATE TABLE COOKIEMAKERS(
    CookieMaker_id NUMBER,
    time_to_wait NUMBER,
    ammount NUMBER,
    cost NUMBER,
    img_path VARCHAR2(100),
    Player_id NUMBER,
    How_many NUMBER,
    CONSTRAINT FK_PLAYER_COOKIEMAKERS FOREIGN KEY (Player_id) REFERENCES PLAYERS (Player_id),
    CONSTRAINT PK_COOKIEMAKER PRIMARY KEY (CookieMaker_id)
);
SELECT * FROM COOKIEMAKERS;

CREATE TABLE CURSORS(
    Cursor_id NUMBER,
    CONSTRAINT PK_CURSOR PRIMARY KEY (Cursor_id),
    CONSTRAINT FK_CURSOR FOREIGN KEY (Cursor_id) REFERENCES COOKIEMAKERS (CookieMaker_id)
);


CREATE TABLE GRANDMAS(
    Grandma_id NUMBER,
    CONSTRAINT PK_GRANDMA PRIMARY KEY (Grandma_id),
    CONSTRAINT FK_GRANDMA FOREIGN KEY (Grandma_id) REFERENCES COOKIEMAKERS (CookieMaker_id)
);


-- CREATE SEQUENCE seq_GAMESERVICE
-- INCREMENT BY 1
-- START WITH 1;
--
-- CREATE TABLE GAMESERVICES(
--     GameService_id NUMBER,
--     Player_id NUMBER,
--     CONSTRAINT PK_GAMESERVICE PRIMARY KEY (GameService_id),
--     CONSTRAINT FK_PLAYER_GAMESERVICE FOREIGN KEY (Player_id) REFERENCES PLAYERS (Player_id)
-- );





