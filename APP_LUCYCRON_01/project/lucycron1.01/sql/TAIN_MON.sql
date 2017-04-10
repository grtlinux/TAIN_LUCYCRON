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
-- TB_FEPGRP
DROP TABLE KANG.TB_FEPGRP;

CREATE TABLE KANG.TB_FEPGRP
(
	F_GRPID    VARCHAR(6)    NOT NULL  DEFAULT ''                ,  -- FEPID group id
	F_TYPNM    VARCHAR(6)              DEFAULT ''                ,  -- type ONLINE/BATCH
	F_GRPNM    VARCHAR(200)            DEFAULT ''                ,  -- FEPID group name
	F_DESC     VARCHAR(200)            DEFAULT ''                ,  -- description
	F_CMMT     VARCHAR(200)            DEFAULT ''                ,  -- comment
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_FEPGRP ADD CONSTRAINT PK_FEPGRP PRIMARY KEY ( F_GRPID );

INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPBNK', 'ONLINE', '은행그룹'  , '은행그룹들'        , '은행그룹들'        );
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPCRD', 'ONLINE', '카드그룹'  , '카드그룹들'        , '카드그룹들'        );
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPKFT', 'ONLINE', '금융결제원', '금융결제원들'      , '금융결제원들'      );
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPETC', 'ONLINE', '기타그룹'  , '기타그룹들'        , '기타그룹들'        );
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPBKO', 'ONLINE', '방카(ON)'  , '방카그룹들(온라인)', '방카그룹들(온라인)');
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPBKB', 'BATCH' , '방카(BAT)' , '방카그룹들(배치)'  , '방카그룹들(배치)'  );

-- ==================================================================================================
-- TB_FEPINFO
DROP TABLE KANG.TB_FEPINFO;

CREATE TABLE KANG.TB_FEPINFO
(
	F_FEPID    VARCHAR(5)    NOT NULL  DEFAULT ''                ,  -- FEP id
	F_GRPID    VARCHAR(6)              DEFAULT ''                ,  -- group type ONLINE/BATCH
	F_TYPNM    VARCHAR(6)              DEFAULT ''                ,  -- type ONLINE/BATCH
	F_FEPNM    VARCHAR(200)            DEFAULT ''                ,  -- FEP name
	F_DESC     VARCHAR(200)            DEFAULT ''                ,  -- description
	F_CMMT     VARCHAR(200)            DEFAULT ''                ,  -- comment
	DTTM_REG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ,  -- create timestamp
	DTTM_CHG   TIMESTAMP               DEFAULT CURRENT_TIMESTAMP    -- change timestamp
);

ALTER TABLE KANG.TB_FEPINFO ADD CONSTRAINT PK_FEPINFO PRIMARY KEY ( F_FEPID );

INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KBB01', 'GRPBNK', 'ONLINE', '국민은행'         , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHB01', 'GRPBNK', 'ONLINE', '신한은행'         , '펌뱅킹'                , '펌뱅킹'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB01', 'GRPBNK', 'ONLINE', '우리은행'         , '이체'                  , '이체'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB02', 'GRPBNK', 'ONLINE', '우리은행'         , '서비스'                , '서비스'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB03', 'GRPBNK', 'ONLINE', '우리은행'         , '자동이체'              , '자동이체'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB04', 'GRPBNK', 'ONLINE', '우리은행'         , '보통예금'              , '보통예금'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB05', 'GRPBNK', 'ONLINE', '우리은행'         , '자금집금'              , '자금집금'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB06', 'GRPBNK', 'ONLINE', '우리은행'         , 'RealTime'              , 'RealTime'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB07', 'GRPBNK', 'ONLINE', '우리은행'         , '퇴직연금'              , '퇴직연금'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB08', 'GRPBNK', 'ONLINE', '우리은행'         , '가상계좌'              , '가상계좌'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('TKB01', 'GRPBNK', 'ONLINE', '대구은행'         , '이체'                  , '이체'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('EXB01', 'GRPBNK', 'ONLINE', '외환은행'         , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('EXB02', 'GRPBNK', 'ONLINE', '외환은행'         , '환율정보수신'          , '환율정보수신'          );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNB01', 'GRPBNK', 'ONLINE', '하나은행'         , '이체'                  , '이체'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNB02', 'GRPBNK', 'ONLINE', '하나은행'         , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('PSB01', 'GRPBNK', 'ONLINE', '부산은행'         , '이체'                  , '이체'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('POS01', 'GRPBNK', 'ONLINE', '우체국'           , '제휴'                  , '제휴'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('NHB01', 'GRPBNK', 'ONLINE', '농협'             , '예수금(연계)'          , '예수금(연계)'          );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('NHB02', 'GRPBNK', 'ONLINE', '농협'             , '가상(이체)'            , '가상(이체)'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('NHB03', 'GRPBNK', 'ONLINE', '농협'             , '연계(예수금)'          , '연계(예수금)'          );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('GUB01', 'GRPBNK', 'ONLINE', '기업은행'         , '연계(AP01,AP02)'       , '연계(AP01,AP02)'       );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SCB01', 'GRPBNK', 'ONLINE', 'SC제일은행'       , '이체(Xecure.)'         , '이체(Xecure.)'         );

INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHC01', 'GRPCRD', 'ONLINE', '신한카드'         , '온라인TR'              , '온라인TR'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHC02', 'GRPCRD', 'ONLINE', '신한카드'         , '온라인BL'              , '온라인BL'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHC03', 'GRPCRD', 'ONLINE', '신한카드'         , '신청'                  , '신청'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHC04', 'GRPCRD', 'ONLINE', '신한카드'         , '펌뱅킹'                , '펌뱅킹'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HDC01', 'GRPCRD', 'ONLINE', '현대카드'         , '승인입출금'            , '승인입출금'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HDC02', 'GRPCRD', 'ONLINE', '현대카드'         , '발급/비번등록'         , '발급/비번등록'         );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HDC03', 'GRPCRD', 'ONLINE', '현대카드'         , 'BL'                    , 'BL'                    );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HDC04', 'GRPCRD', 'ONLINE', '현대카드'         , 'CMA신용카드'           , 'CMA신용카드'           );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('LTC01', 'GRPCRD', 'ONLINE', '롯데카드'         , '신청/접수진행'         , '신청/접수진행'         );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('LTC02', 'GRPCRD', 'ONLINE', '롯데카드'         , 'BL'                    , 'BL'                    );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('CTC01', 'GRPCRD', 'ONLINE', '시티카드'         , '제휴'                  , '제휴'                  );
                                                                                                                                                                                             
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT01', 'GRPKFT', 'ONLINE', '금융결제원'       , '타행환 (07~18)'        , '타행환 (07~18)'        );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT02', 'GRPKFT', 'ONLINE', '금융결제원'       , 'CD/ATM'                , 'CD/ATM'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT03', 'GRPKFT', 'ONLINE', '금융결제원'       , '전자금융'              , '전자금융'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT04', 'GRPKFT', 'ONLINE', '금융결제원'       , '대고객서비스(ARS)'     , '대고객서비스(ARS)'     );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT05', 'GRPKFT', 'ONLINE', '금융결제원'       , '인터넷지로'            , '인터넷지로'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT06', 'GRPKFT', 'ONLINE', '금융결제원'       , '실시간지로 (09~23)'    , '실시간지로 (09~23)'    );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT07', 'GRPKFT', 'ONLINE', '금융결제원'       , 'CMS부가서비스'         , 'CMS부가서비스'         );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT08', 'GRPKFT', 'ONLINE', '금융결제원'       , 'PG'                    , 'PG'                    );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT09', 'GRPKFT', 'ONLINE', '금융결제원'       , 'CMS부가(한화증권)'     , 'CMS부가(한화증권)'     );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT10', 'GRPKFT', 'ONLINE', '금융결제원'       , 'CMS부가(프루덴셜)'     , 'CMS부가(프루덴셜)'     );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT11', 'GRPKFT', 'ONLINE', '금융결제원'       , '인터넷지로-타발'       , '인터넷지로-타발'       );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT12', 'GRPKFT', 'ONLINE', '금융결제원'       , '실시간지로-타발(09~23)', '실시간지로-타발(09~23)');
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT13', 'GRPKFT', 'ONLINE', '금융결제원'       , '펌뱅킹(BC)'            , '펌뱅킹(BC)'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT14', 'GRPKFT', 'ONLINE', '금융결제원'       , '자금반환청구'          , '자금반환청구'          );
                                                                                                                                                                                             
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('DHI01', 'GRPETC', 'ONLINE', '대한생명'         , '신용대출'              , '신용대출'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('DHI02', 'GRPETC', 'ONLINE', '대한생명'         , 'ATM입출금'             , 'ATM입출금'             );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('DHI03', 'GRPETC', 'ONLINE', '대한생명'         , '자동납입'              , '자동납입'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KIS01', 'GRPETC', 'ONLINE', '나이스'           , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KIS02', 'GRPETC', 'ONLINE', '나이스'           , '휴대폰인증'            , '휴대폰인증'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNT01', 'GRPETC', 'ONLINE', '한네트'           , '점외CD'                , '점외CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('GBK01', 'GRPETC', 'ONLINE', '훼미리, BGF캐쉬넷', '점외CD'                , '점외CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HYS01', 'GRPETC', 'ONLINE', '효성'             , '점외CD (080-920-2220)' , '점외CD (080-920-2220)' );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('CHO01', 'GRPETC', 'ONLINE', '청호'             , '점외CD'                , '점외CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KBK01', 'GRPETC', 'ONLINE', 'KISBANK'          , '점외CD'                , '점외CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('LOT01', 'GRPETC', 'ONLINE', '롯데'             , '점외CD'                , '점외CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SSN01', 'GRPETC', 'ONLINE', '삼성네트웍'       , '펌뱅킹'                , '펌뱅킹'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KSN01', 'GRPETC', 'ONLINE', 'KS-NET'           , '펌뱅킹'                , '펌뱅킹'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KSN02', 'GRPETC', 'ONLINE', 'KS-NET'           , '펌뱅킹-자금팀'         , '펌뱅킹-자금팀'         );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('LGU01', 'GRPETC', 'ONLINE', 'LG유플러스'       , 'PG (필요시접속)'       , 'PG (필요시접속)'       );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('TAT01', 'GRPETC', 'ONLINE', '씽크에이티'       , '2채널인증'             , '2채널인증'             );
                                                                                                                                                                                             
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HWI01', 'GRPBKO', 'ONLINE', '한화생명'         , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHI01', 'GRPBKO', 'ONLINE', '신한생명'         , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNI01', 'GRPBKO', 'ONLINE', '하나HSBC생명'     , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HKI01', 'GRPBKO', 'ONLINE', '흥국생명'         , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFI01', 'GRPBKO', 'ONLINE', '카디프생명'       , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('ALI01', 'GRPBKO', 'ONLINE', '알리안츠생명'     , '연계'                  , '연계'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SSI01', 'GRPBKO', 'ONLINE', '삼성생명'         , '연계'                  , '연계'                  );
                                                                                                                                                                                             
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HWI51', 'GRPBKB', 'BATCH' , '한화생명'         , '일괄배치'              , '일괄배치'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHI51', 'GRPBKB', 'BATCH' , '신한생명'         , '일괄배치'              , '일괄배치'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNI51', 'GRPBKB', 'BATCH' , '하나HSBC생명'     , '일괄배치'              , '일괄배치'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HKI51', 'GRPBKB', 'BATCH' , '흥국생명'         , '일괄배치'              , '일괄배치'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFI51', 'GRPBKB', 'BATCH' , '카디프생명'       , '일괄배치'              , '일괄배치'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('ALI51', 'GRPBKB', 'BATCH' , '알리안츠생명'     , '일괄배치'              , '일괄배치'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SSI51', 'GRPBKB', 'BATCH' , '삼성생명'         , '일괄배치'              , '일괄배치'              );




-- ==================================================================================================
-- commit
COMMIT;




