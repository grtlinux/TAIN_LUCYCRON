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
-- TB_RUNCWD
DROP TABLE KANG.TB_RUNCWD;

CREATE TABLE KANG.TB_RUNCWD
(
	F_RUNID    VARCHAR(10)   NOT NULL  DEFAULT ''                ,  -- RUNID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_CWD      VARCHAR(1024)           DEFAULT ''                ,  -- current working directory
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_RUNCWD ADD CONSTRAINT PK_RUNCWD PRIMARY KEY ( F_RUNID, F_SEQ );

-- ==================================================================================================
-- TB_RUNCMD
DROP TABLE KANG.TB_RUNCMD;

CREATE TABLE KANG.TB_RUNCMD
(
	F_RUNID    VARCHAR(10)   NOT NULL  DEFAULT ''                ,  -- RUNID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_CMD      VARCHAR(1024)           DEFAULT ''                ,  -- command
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_RUNCMD ADD CONSTRAINT PK_RUNCMD PRIMARY KEY ( F_RUNID, F_SEQ );

-- ==================================================================================================
-- TB_RUNENV
DROP TABLE KANG.TB_RUNENV;

CREATE TABLE KANG.TB_RUNENV
(
	F_RUNID    VARCHAR(10)   NOT NULL  DEFAULT ''                ,  -- RUNID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_ENV      VARCHAR(1024)           DEFAULT ''                ,  -- environment
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_RUNENV ADD CONSTRAINT PK_RUNENV PRIMARY KEY ( F_RUNID, F_SEQ );

-- ==================================================================================================
-- TB_RUNHHMM
DROP TABLE KANG.TB_RUNHHMM;

CREATE TABLE KANG.TB_RUNHHMM
(
	F_RUNID    VARCHAR(10)   NOT NULL  DEFAULT ''                ,  -- RUNID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_HHMM     VARCHAR(1024)           DEFAULT ''                ,  -- schedule time(HH:MM)
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_RUNHHMM ADD CONSTRAINT PK_RUNHHMM PRIMARY KEY ( F_RUNID, F_SEQ );

-- ==================================================================================================
-- TB_RUNPROP
DROP TABLE KANG.TB_RUNPROP;

CREATE TABLE KANG.TB_RUNPROP
(
	F_RUNID    VARCHAR(10)   NOT NULL  DEFAULT ''                ,  -- RUNID
	F_SEQ      INTEGER       NOT NULL  DEFAULT 0                 ,  -- sequence
	F_PROP     VARCHAR(1024)           DEFAULT ''                ,  -- property
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_RUNPROP ADD CONSTRAINT PK_RUNPROP PRIMARY KEY ( F_RUNID, F_SEQ );

-- ==================================================================================================
-- ==================================================================================================
-- TB_SCHDTTM
DROP TABLE KANG.TB_SCHDTTM;

CREATE TABLE KANG.TB_SCHDTTM
(
	F_HHMM     VARCHAR(5)    NOT NULL  DEFAULT ''                ,  -- schedule time(HH:MM)
	F_RUNID    VARCHAR(10)   NOT NULL  DEFAULT ''                ,  -- RUNID
	F_RUNDTTM  TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- running timestamp
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_SCHDTTM ADD CONSTRAINT PK_SCHDTTM PRIMARY KEY ( F_HHMM, F_RUNID );

-- IN_SCHDTTM
-- DROP INDEX KANG.IN_SCHDTTM;
CREATE INDEX KANG.IN_SCHDTTM ON KANG.TB_SCHDTTM   ( F_RUNID ASC );

-- ==================================================================================================
-- TB_SCHLOG
DROP TABLE KANG.TB_SCHLOG;

CREATE TABLE KANG.TB_SCHLOG
(
	F_RUNDTTM  TIMESTAMP     NOT NULL  DEFAULT CURRENT_TIMESTAMP ,  -- running timestamp

	F_HHMM     VARCHAR(5)              DEFAULT ''                ,  -- schedule time(HH:MM)
	F_RUNID    VARCHAR(10)             DEFAULT ''                ,  -- RUNID
--	F_SEQ      INTEGER                 DEFAULT 0                 ,  -- sequence
	F_RSLT     VARCHAR(1024)           DEFAULT ''                ,  -- property
	
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

-- ALTER TABLE KANG.TB_RUNPROP ADD CONSTRAINT PK_RUNPROP PRIMARY KEY ( F_RUNID, F_SEQ );




-- ==================================================================================================
-- commit
COMMIT;




