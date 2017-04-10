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

INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPBNK', 'ONLINE', '����׷�'  , '����׷��'        , '����׷��'        );
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPCRD', 'ONLINE', 'ī��׷�'  , 'ī��׷��'        , 'ī��׷��'        );
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPKFT', 'ONLINE', '����������', '������������'      , '������������'      );
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPETC', 'ONLINE', '��Ÿ�׷�'  , '��Ÿ�׷��'        , '��Ÿ�׷��'        );
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPBKO', 'ONLINE', '��ī(ON)'  , '��ī�׷��(�¶���)', '��ī�׷��(�¶���)');
INSERT INTO KANG.TB_FEPGRP (F_GRPID, F_TYPNM, F_GRPNM, F_DESC, F_CMMT) VALUES ('GRPBKB', 'BATCH' , '��ī(BAT)' , '��ī�׷��(��ġ)'  , '��ī�׷��(��ġ)'  );

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

INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KBB01', 'GRPBNK', 'ONLINE', '��������'         , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHB01', 'GRPBNK', 'ONLINE', '��������'         , '�߹�ŷ'                , '�߹�ŷ'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB01', 'GRPBNK', 'ONLINE', '�츮����'         , '��ü'                  , '��ü'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB02', 'GRPBNK', 'ONLINE', '�츮����'         , '����'                , '����'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB03', 'GRPBNK', 'ONLINE', '�츮����'         , '�ڵ���ü'              , '�ڵ���ü'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB04', 'GRPBNK', 'ONLINE', '�츮����'         , '���뿹��'              , '���뿹��'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB05', 'GRPBNK', 'ONLINE', '�츮����'         , '�ڱ�����'              , '�ڱ�����'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB06', 'GRPBNK', 'ONLINE', '�츮����'         , 'RealTime'              , 'RealTime'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB07', 'GRPBNK', 'ONLINE', '�츮����'         , '��������'              , '��������'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('WRB08', 'GRPBNK', 'ONLINE', '�츮����'         , '�������'              , '�������'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('TKB01', 'GRPBNK', 'ONLINE', '�뱸����'         , '��ü'                  , '��ü'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('EXB01', 'GRPBNK', 'ONLINE', '��ȯ����'         , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('EXB02', 'GRPBNK', 'ONLINE', '��ȯ����'         , 'ȯ����������'          , 'ȯ����������'          );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNB01', 'GRPBNK', 'ONLINE', '�ϳ�����'         , '��ü'                  , '��ü'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNB02', 'GRPBNK', 'ONLINE', '�ϳ�����'         , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('PSB01', 'GRPBNK', 'ONLINE', '�λ�����'         , '��ü'                  , '��ü'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('POS01', 'GRPBNK', 'ONLINE', '��ü��'           , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('NHB01', 'GRPBNK', 'ONLINE', '����'             , '������(����)'          , '������(����)'          );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('NHB02', 'GRPBNK', 'ONLINE', '����'             , '����(��ü)'            , '����(��ü)'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('NHB03', 'GRPBNK', 'ONLINE', '����'             , '����(������)'          , '����(������)'          );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('GUB01', 'GRPBNK', 'ONLINE', '�������'         , '����(AP01,AP02)'       , '����(AP01,AP02)'       );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SCB01', 'GRPBNK', 'ONLINE', 'SC��������'       , '��ü(Xecure.)'         , '��ü(Xecure.)'         );

INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHC01', 'GRPCRD', 'ONLINE', '����ī��'         , '�¶���TR'              , '�¶���TR'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHC02', 'GRPCRD', 'ONLINE', '����ī��'         , '�¶���BL'              , '�¶���BL'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHC03', 'GRPCRD', 'ONLINE', '����ī��'         , '��û'                  , '��û'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHC04', 'GRPCRD', 'ONLINE', '����ī��'         , '�߹�ŷ'                , '�߹�ŷ'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HDC01', 'GRPCRD', 'ONLINE', '����ī��'         , '���������'            , '���������'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HDC02', 'GRPCRD', 'ONLINE', '����ī��'         , '�߱�/������'         , '�߱�/������'         );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HDC03', 'GRPCRD', 'ONLINE', '����ī��'         , 'BL'                    , 'BL'                    );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HDC04', 'GRPCRD', 'ONLINE', '����ī��'         , 'CMA�ſ�ī��'           , 'CMA�ſ�ī��'           );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('LTC01', 'GRPCRD', 'ONLINE', '�Ե�ī��'         , '��û/��������'         , '��û/��������'         );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('LTC02', 'GRPCRD', 'ONLINE', '�Ե�ī��'         , 'BL'                    , 'BL'                    );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('CTC01', 'GRPCRD', 'ONLINE', '��Ƽī��'         , '����'                  , '����'                  );
                                                                                                                                                                                             
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT01', 'GRPKFT', 'ONLINE', '����������'       , 'Ÿ��ȯ (07~18)'        , 'Ÿ��ȯ (07~18)'        );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT02', 'GRPKFT', 'ONLINE', '����������'       , 'CD/ATM'                , 'CD/ATM'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT03', 'GRPKFT', 'ONLINE', '����������'       , '���ڱ���'              , '���ڱ���'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT04', 'GRPKFT', 'ONLINE', '����������'       , '�������(ARS)'     , '�������(ARS)'     );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT05', 'GRPKFT', 'ONLINE', '����������'       , '���ͳ�����'            , '���ͳ�����'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT06', 'GRPKFT', 'ONLINE', '����������'       , '�ǽð����� (09~23)'    , '�ǽð����� (09~23)'    );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT07', 'GRPKFT', 'ONLINE', '����������'       , 'CMS�ΰ�����'         , 'CMS�ΰ�����'         );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT08', 'GRPKFT', 'ONLINE', '����������'       , 'PG'                    , 'PG'                    );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT09', 'GRPKFT', 'ONLINE', '����������'       , 'CMS�ΰ�(��ȭ����)'     , 'CMS�ΰ�(��ȭ����)'     );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT10', 'GRPKFT', 'ONLINE', '����������'       , 'CMS�ΰ�(���絧��)'     , 'CMS�ΰ�(���絧��)'     );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT11', 'GRPKFT', 'ONLINE', '����������'       , '���ͳ�����-Ÿ��'       , '���ͳ�����-Ÿ��'       );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT12', 'GRPKFT', 'ONLINE', '����������'       , '�ǽð�����-Ÿ��(09~23)', '�ǽð�����-Ÿ��(09~23)');
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT13', 'GRPKFT', 'ONLINE', '����������'       , '�߹�ŷ(BC)'            , '�߹�ŷ(BC)'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFT14', 'GRPKFT', 'ONLINE', '����������'       , '�ڱݹ�ȯû��'          , '�ڱݹ�ȯû��'          );
                                                                                                                                                                                             
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('DHI01', 'GRPETC', 'ONLINE', '���ѻ���'         , '�ſ����'              , '�ſ����'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('DHI02', 'GRPETC', 'ONLINE', '���ѻ���'         , 'ATM�����'             , 'ATM�����'             );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('DHI03', 'GRPETC', 'ONLINE', '���ѻ���'         , '�ڵ�����'              , '�ڵ�����'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KIS01', 'GRPETC', 'ONLINE', '���̽�'           , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KIS02', 'GRPETC', 'ONLINE', '���̽�'           , '�޴�������'            , '�޴�������'            );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNT01', 'GRPETC', 'ONLINE', '�ѳ�Ʈ'           , '����CD'                , '����CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('GBK01', 'GRPETC', 'ONLINE', '�ѹ̸�, BGFĳ����', '����CD'                , '����CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HYS01', 'GRPETC', 'ONLINE', 'ȿ��'             , '����CD (080-920-2220)' , '����CD (080-920-2220)' );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('CHO01', 'GRPETC', 'ONLINE', 'ûȣ'             , '����CD'                , '����CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KBK01', 'GRPETC', 'ONLINE', 'KISBANK'          , '����CD'                , '����CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('LOT01', 'GRPETC', 'ONLINE', '�Ե�'             , '����CD'                , '����CD'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SSN01', 'GRPETC', 'ONLINE', '�Ｚ��Ʈ��'       , '�߹�ŷ'                , '�߹�ŷ'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KSN01', 'GRPETC', 'ONLINE', 'KS-NET'           , '�߹�ŷ'                , '�߹�ŷ'                );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KSN02', 'GRPETC', 'ONLINE', 'KS-NET'           , '�߹�ŷ-�ڱ���'         , '�߹�ŷ-�ڱ���'         );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('LGU01', 'GRPETC', 'ONLINE', 'LG���÷���'       , 'PG (�ʿ������)'       , 'PG (�ʿ������)'       );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('TAT01', 'GRPETC', 'ONLINE', '��ũ����Ƽ'       , '2ä������'             , '2ä������'             );
                                                                                                                                                                                             
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HWI01', 'GRPBKO', 'ONLINE', '��ȭ����'         , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHI01', 'GRPBKO', 'ONLINE', '���ѻ���'         , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNI01', 'GRPBKO', 'ONLINE', '�ϳ�HSBC����'     , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HKI01', 'GRPBKO', 'ONLINE', '�ﱹ����'         , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFI01', 'GRPBKO', 'ONLINE', 'ī��������'       , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('ALI01', 'GRPBKO', 'ONLINE', '�˸���������'     , '����'                  , '����'                  );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SSI01', 'GRPBKO', 'ONLINE', '�Ｚ����'         , '����'                  , '����'                  );
                                                                                                                                                                                             
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HWI51', 'GRPBKB', 'BATCH' , '��ȭ����'         , '�ϰ���ġ'              , '�ϰ���ġ'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SHI51', 'GRPBKB', 'BATCH' , '���ѻ���'         , '�ϰ���ġ'              , '�ϰ���ġ'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HNI51', 'GRPBKB', 'BATCH' , '�ϳ�HSBC����'     , '�ϰ���ġ'              , '�ϰ���ġ'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('HKI51', 'GRPBKB', 'BATCH' , '�ﱹ����'         , '�ϰ���ġ'              , '�ϰ���ġ'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('KFI51', 'GRPBKB', 'BATCH' , 'ī��������'       , '�ϰ���ġ'              , '�ϰ���ġ'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('ALI51', 'GRPBKB', 'BATCH' , '�˸���������'     , '�ϰ���ġ'              , '�ϰ���ġ'              );
INSERT INTO KANG.TB_FEPINFO (F_FEPID, F_GRPID, F_TYPNM, F_FEPNM, F_DESC, F_CMMT) VALUES ('SSI51', 'GRPBKB', 'BATCH' , '�Ｚ����'         , '�ϰ���ġ'              , '�ϰ���ġ'              );




-- ==================================================================================================
-- commit
COMMIT;




