-- Drop tables if they exist to avoid conflicts
DROP TABLE GRANDMAS CASCADE CONSTRAINTS;
DROP TABLE CURSORS CASCADE CONSTRAINTS;
DROP TABLE COOKIEMAKERS CASCADE CONSTRAINTS;
DROP TABLE ACHIEVEMENTS CASCADE CONSTRAINTS;
DROP TABLE PLAYERS CASCADE CONSTRAINTS;
DROP TABLE COOKIECLICKERS CASCADE CONSTRAINTS;
DROP SEQUENCE seq_PLAYER;
DROP SEQUENCE seq_CookieClicker;
DROP SEQUENCE seq_achievement;
DROP SEQUENCE seq_COOKIEMAKER;

-- Create sequences
CREATE SEQUENCE seq_CookieClicker
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE seq_PLAYER
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE seq_achievement
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE seq_COOKIEMAKER
    INCREMENT BY 1
    START WITH 1;

-- Create the COOKIECLICKERS table
CREATE TABLE COOKIECLICKERS (
                                CookieClicker_id NUMBER PRIMARY KEY,
                                cookies_per_click NUMBER
);

-- Create the PLAYERS table with ON DELETE CASCADE
CREATE TABLE PLAYERS (
                         Player_id NUMBER PRIMARY KEY,
                         Name VARCHAR2(100) UNIQUE NOT NULL,
                         Cookies NUMBER,
                         CookieClicker_id NUMBER,
                         CONSTRAINT FK_COOKIECLICKER FOREIGN KEY (CookieClicker_id) REFERENCES COOKIECLICKERS (CookieClicker_id)
);


-- Create the ACHIEVEMENTS table with ON DELETE CASCADE
CREATE TABLE ACHIEVEMENTS (
                              Achievement_id NUMBER PRIMARY KEY,
                              Name VARCHAR2(100),
                              Description VARCHAR2(1000),
                              Achieved NUMBER(1),
                              Player_id NUMBER,
                              CONSTRAINT FK_PLAYER_ACHIEVEMENTS FOREIGN KEY (Player_id) REFERENCES PLAYERS (Player_id) ON DELETE CASCADE
);

-- Create the COOKIEMAKERS table with ON DELETE CASCADE
CREATE TABLE COOKIEMAKERS (
                              CookieMaker_id NUMBER PRIMARY KEY,
                              time_to_wait NUMBER,
                              ammount NUMBER,
                              cost NUMBER,
                              img_path VARCHAR2(100),
                              Player_id NUMBER,
                              How_many NUMBER,
                              CONSTRAINT FK_PLAYER_COOKIEMAKERS FOREIGN KEY (Player_id) REFERENCES PLAYERS (Player_id) ON DELETE CASCADE
);

-- Create the CURSORS table with ON DELETE CASCADE
CREATE TABLE CURSORS (
                         Cursor_id NUMBER PRIMARY KEY,
                         CONSTRAINT FK_CURSOR FOREIGN KEY (Cursor_id) REFERENCES COOKIEMAKERS (CookieMaker_id) ON DELETE CASCADE
);

-- Create the GRANDMAS table with ON DELETE CASCADE
CREATE TABLE GRANDMAS (
                          Grandma_id NUMBER PRIMARY KEY,
                          CONSTRAINT FK_GRANDMA FOREIGN KEY (Grandma_id) REFERENCES COOKIEMAKERS (CookieMaker_id) ON DELETE CASCADE
);

-- -- Verify the data
-- SELECT * FROM PLAYERS;
-- SELECT * FROM COOKIECLICKERS;
-- SELECT * FROM ACHIEVEMENTS;
-- SELECT * FROM COOKIEMAKERS;
