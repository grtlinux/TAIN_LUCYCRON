-- Licensed to the Apache Software Foundation (ASF) under one or more
-- contributor license agreements.  See the NOTICE file distributed with
-- this work for additional information regarding copyright ownership.
-- The ASF licenses this file to You under the Apache License, Version 2.0
-- (the "License"); you may not use this file except in compliance with
-- the License.  You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.

-- schema : KANG

AUTOCOMMIT OFF;

-- ==================================================================================================
-- ==================================================================================================
-- ==================================================================================================
-- ==================================================================================================
-- ==================================================================================================
-- ==================================================================================================
-- TB_SCHCWD
DROP TABLE KANG.TB_SCHCWD;

CREATE TABLE KANG.TB_SCHCWD
(
	F_SCHID    VARCHAR(128)  NOT NULL  DEFAULT ''                ,  -- SCHID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_CWD      VARCHAR(1024)           DEFAULT ''                ,  -- current working directory
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_SCHCWD ADD CONSTRAINT PK_SCHCWD PRIMARY KEY ( F_SCHID, F_SEQ );

-- ==================================================================================================
-- TB_SCHCMD
DROP TABLE KANG.TB_SCHCMD;

CREATE TABLE KANG.TB_SCHCMD
(
	F_SCHID    VARCHAR(128)  NOT NULL  DEFAULT ''                ,  -- SCHID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_CMD      VARCHAR(1024)           DEFAULT ''                ,  -- command
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_SCHCMD ADD CONSTRAINT PK_SCHCMD PRIMARY KEY ( F_SCHID, F_SEQ );

-- ==================================================================================================
-- TB_SCHENV
DROP TABLE KANG.TB_SCHENV;

CREATE TABLE KANG.TB_SCHENV
(
	F_SCHID    VARCHAR(128)  NOT NULL  DEFAULT ''                ,  -- SCHID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_ENV      VARCHAR(4096)           DEFAULT ''                ,  -- environment
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_SCHENV ADD CONSTRAINT PK_SCHENV PRIMARY KEY ( F_SCHID, F_SEQ );

-- ==================================================================================================
-- TB_SCHHHMM
DROP TABLE KANG.TB_SCHHHMM;

CREATE TABLE KANG.TB_SCHHHMM
(
	F_SCHID    VARCHAR(128)  NOT NULL  DEFAULT ''                ,  -- SCHID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_HHMM     VARCHAR(5)              DEFAULT ''                ,  -- schedule time(HH:MM)
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_SCHHHMM ADD CONSTRAINT PK_SCHHHMM PRIMARY KEY ( F_SCHID, F_SEQ );

-- IN_SCHHHMM
-- DROP INDEX KANG.IN_SCHHHMM;
CREATE INDEX KANG.IN_SCHHHMM ON KANG.TB_SCHHHMM ( F_SCHID ASC );

-- ==================================================================================================
-- ==================================================================================================
-- TB_RUNLOG
DROP TABLE KANG.TB_RUNLOG;

CREATE TABLE KANG.TB_RUNLOG
(
	F_RUNDTTM  TIMESTAMP     NOT NULL  DEFAULT CURRENT_TIMESTAMP ,  -- running timestamp

	F_SCHID    VARCHAR(128)  NOT NULL  DEFAULT ''                ,  -- SCHID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_HHMM     VARCHAR(5)              DEFAULT ''                ,  -- schedule time(HH:MM)

	F_RSLT     VARCHAR(1024)           DEFAULT ''                ,  -- property
	
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

-- IN_RUNLOG
-- DROP INDEX KANG.IN_RUNLOG;
CREATE INDEX KANG.IN_RUNLOG ON KANG.TB_RUNLOG ( F_SCHID ASC );



-- ==================================================================================================
-- ==================================================================================================
-- TABLE COUNT

SELECT 'TB_SCHCWD' AS TB_NAME, COUNT(*) AS TB_CNT FROM KANG.TB_SCHCWD
UNION SELECT 'TB_SCHCMD'  AS TB_NAME, COUNT(*) AS TB_CNT FROM KANG.TB_SCHCMD
UNION SELECT 'TB_SCHENV'  AS TB_NAME, COUNT(*) AS TB_CNT FROM KANG.TB_SCHENV
UNION SELECT 'TB_SCHHHMM' AS TB_NAME, COUNT(*) AS TB_CNT FROM KANG.TB_SCHHHMM
UNION SELECT 'TB_RUNLOG'  AS TB_NAME, COUNT(*) AS TB_CNT FROM KANG.TB_RUNLOG
;





-- ==================================================================================================
-- commit
COMMIT;




