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

-- # -- ==================================================================================================
-- # -- ==================================================================================================
-- # -- ==================================================================================================
-- # -- ==================================================================================================
-- # -- ==================================================================================================
-- # -- ==================================================================================================
-- # -- TB_CPUINFO
-- # DROP TABLE KANG.TB_CPUINFO;  -- drop table and index
-- # 
-- # CREATE TABLE KANG.TB_CPUINFO
-- # (
-- # 	F_VNDR     VARCHAR(100)  NOT NULL  DEFAULT ''                ,  -- Vender
-- # 	F_MDL      VARCHAR(100)  NOT NULL  DEFAULT ''                ,  -- Model
-- # 	F_MHZ      INTEGER                 DEFAULT 0                 ,  -- MHz
-- # 	F_TTL      INTEGER                 DEFAULT 0                 ,  -- Total CPUs
-- # 	F_PHS      INTEGER                 DEFAULT 0                 ,  -- Physical CPUs
-- # 	F_CPC      INTEGER                 DEFAULT 0                 ,  -- Cores per CPU
-- # 	F_YN       CHAR(1)                 DEFAULT 'Y'               ,  -- usable flag
-- # 	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
-- # 	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
-- # );
-- # 
-- # INSERT INTO KANG.TB_CPUINFO ( F_VNDR, F_MDL ) VALUES ( 'Vender', 'Model' );
-- # 
-- # -- ==================================================================================================
-- # -- TB_CPUREC
-- # DROP TABLE KANG.TB_CPUREC;
-- # 
-- # CREATE TABLE KANG.TB_CPUREC
-- # (
-- # 	F_DTTM     TIMESTAMP     NOT NULL  DEFAULT CURRENT_TIMESTAMP ,  -- timestamp YYYY-MM-DD HH:MM:00
-- # 	F_CPUNM    VARCHAR(10)   NOT NULL  DEFAULT ''                ,  -- CPU name (CPU0,CPU1,..TOTAL)
-- # 	F_USR      DOUBLE                  DEFAULT 0                 ,  -- User Time (%)
-- # 	F_SYS      DOUBLE                  DEFAULT 0                 ,  -- System Time (%)
-- # 	F_IDL      DOUBLE                  DEFAULT 0                 ,  -- Idle time (%)
-- # 	F_WAIT     DOUBLE                  DEFAULT 0                 ,  -- Wait time (%)
-- # 	F_NCE      DOUBLE                  DEFAULT 0                 ,  -- Nice Time (%)
-- # 	F_CMB      DOUBLE                  DEFAULT 0                 ,  -- Combined (%)
-- # 	F_IRQ      DOUBLE                  DEFAULT 0                 ,  -- Irq time (%)
-- # 	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
-- # 	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
-- # );
-- # 
-- # ALTER TABLE KANG.TB_CPUREC ADD CONSTRAINT PK_CPUREC PRIMARY KEY ( F_DTTM, F_CPUNM );
-- # -- IN_CPUREC
-- # --DROP INDEX KANG.IN_CPUREC;
-- # CREATE INDEX KANG.IN_CPUREC ON KANG.TB_CPUREC   ( F_DTTM ASC );
-- # 
-- # -- ==================================================================================================
-- # -- TB_MEMREC
-- # DROP TABLE KANG.TB_MEMREC;
-- # 
-- # CREATE TABLE KANG.TB_MEMREC
-- # (
-- # 	F_DTTM     TIMESTAMP     NOT NULL  DEFAULT CURRENT_TIMESTAMP ,  -- timestamp YYYY-MM-DD HH:MM:00
-- # 	F_RAM      BIGINT                  DEFAULT 0                 ,  -- mem.getRam
-- # 	F_TTL      BIGINT                  DEFAULT 0                 ,  -- mem.getTotal
-- # 	F_FRE      BIGINT                  DEFAULT 0                 ,  -- mem.getFree
-- # 	F_USE      BIGINT                  DEFAULT 0                 ,  -- mem.getUsed
-- # 	F_FREP     DOUBLE                  DEFAULT 0                 ,  -- mem.getFreePercent
-- # 	F_USEP     DOUBLE                  DEFAULT 0                 ,  -- mem.getUsedPercent
-- # 	F_AFRE     BIGINT                  DEFAULT 0                 ,  -- mem.getActualFree
-- # 	F_AUSE     BIGINT                  DEFAULT 0                 ,  -- mem.getActualUsed
-- # 	F_SWTTL    BIGINT                  DEFAULT 0                 ,  -- swap.getTotal
-- # 	F_SWFRE    BIGINT                  DEFAULT 0                 ,  -- swap.getFree
-- # 	F_SWUSE    BIGINT                  DEFAULT 0                 ,  -- swap.getUsed
-- # 	F_SWPGI    BIGINT                  DEFAULT 0                 ,  -- swap.getPageIn
-- # 	F_SWPGO    BIGINT                  DEFAULT 0                 ,  -- swap.getPageOut
-- # 	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
-- # 	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
-- # );
-- # 
-- # ALTER TABLE KANG.TB_MEMREC ADD CONSTRAINT PK_MEMREC PRIMARY KEY ( F_DTTM );
-- # 
-- # 
-- # -- ==================================================================================================
-- # -- TB_DSKREC
-- # DROP TABLE KANG.TB_DSKREC;
-- # 
-- # CREATE TABLE KANG.TB_DSKREC
-- # (
-- # 	F_DTTM     TIMESTAMP     NOT NULL  DEFAULT CURRENT_TIMESTAMP ,  -- timestamp YYYY-MM-DD HH:MM:00
-- # 	F_DEVNM    VARCHAR(200)  NOT NULL  DEFAULT ''                ,  -- filesystem.getDevName     /dev-
-- # 	F_DIRNM    VARCHAR(200)            DEFAULT ''                ,  -- filesystem.getDirName     /tmp-
-- # 	F_SYSNM    VARCHAR(200)            DEFAULT ''                ,  -- filesystem.getSysTypeName NTFS
-- # 	F_TYPNM    VARCHAR(200)            DEFAULT ''                ,  -- filesystem.getTypeName    local
-- # 	F_TOT      BIGINT                  DEFAULT 0                 ,  -- filesystemusage.getTotal
-- # 	F_USE      BIGINT                  DEFAULT 0                 ,  -- filesystemusage.getTotal - getFree
-- # 	F_AVL      BIGINT                  DEFAULT 0                 ,  -- filesystemusage.getAvail
-- # 	F_USEP     DOUBLE                  DEFAULT 0                 ,  -- filesystemusage.getUsePercent
-- # 	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
-- # 	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
-- # );
-- # 
-- # ALTER TABLE KANG.TB_DSKREC ADD CONSTRAINT PK_DSKREC PRIMARY KEY ( F_DTTM, F_DEVNM );
-- # -- IN_CPUREC
-- # --DROP INDEX KANG.TB_DSKREC;
-- # CREATE INDEX KANG.TB_DSKREC ON KANG.TB_DSKREC   ( F_DTTM ASC );








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
);

ALTER TABLE KANG.TB_SCHDTTM ADD CONSTRAINT PK_SCHDTTM PRIMARY KEY ( F_HHMM, F_RUNID );

-- IN_SCHDTTM
DROP INDEX KANG.IN_SCHDTTM;
CREATE INDEX KANG.IN_SCHDTTM ON KANG.TB_SCHDTTM   ( F_RUNID ASC );

-- ==================================================================================================
-- TB_SCHLOG
DROP TABLE KANG.TB_RUNPROP;

CREATE TABLE KANG.TB_RUNPROP
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




