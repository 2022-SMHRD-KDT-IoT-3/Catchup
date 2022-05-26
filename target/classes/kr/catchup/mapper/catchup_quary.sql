show databases;
use catchup_db;
select * ;
use mysql;


select * from t_user;

-- t_user Table Create SQL
CREATE TABLE t_user
(
    `user_id`        VARCHAR(30)    NOT NULL    COMMENT '회원 아이디', 
    `user_pw`        VARCHAR(30)    NOT NULL    COMMENT '회원 비밀번호', 
    `user_name`      VARCHAR(30)    NOT NULL    COMMENT '회원 이름', 
    `user_nick`      VARCHAR(30)    NOT NULL    COMMENT '회원 닉네임', 
    `user_mail`      VARCHAR(50)    NOT NULL    COMMENT '회원 이메일', 
    `user_serial`    VARCHAR(30)    NOT NULL    COMMENT '회원 사용기계넘버', 
    `user_joindate`  DATETIME       NOT NULL    DEFAULT now() COMMENT '회원 가입일자', 
     PRIMARY KEY (user_id)
);

ALTER TABLE t_user COMMENT '회원 테이블';


-- t_board Table Create SQL
CREATE TABLE t_board
(
    `board_seq`      INT UNSIGNED      NOT NULL    AUTO_INCREMENT COMMENT '게시글 순번', 
    `board_type`     CHAR(1)           NOT NULL    COMMENT '게시글 유형', 
    `board_title`    VARCHAR(200)      NOT NULL    COMMENT '게시글 제목', 
    `board_time`     DATETIME          NOT NULL    DEFAULT now() COMMENT '게시글 작성시간', 
    `board_content`  VARCHAR(4000)     NOT NULL    COMMENT '게시글 내용', 
    `board_cnt`      NUMERIC(12, 0)    NOT NULL    AUTO_INCREMENT COMMENT '게시글 조회수', 
    `board_commcnt`  NUMERIC(12, 0)    NOT NULL    COMMENT '게시글 댓글 수', 
    `user_id`        VARCHAR(30)       NOT NULL    COMMENT '회원 아이디', 
     PRIMARY KEY (board_seq)
);

ALTER TABLE t_board COMMENT '게시판 테이블';

ALTER TABLE t_board
    ADD CONSTRAINT FK_t_board_user_id_t_user_user_id FOREIGN KEY (user_id)
        REFERENCES t_user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- t_reservation Table Create SQL
CREATE TABLE t_reservation
(
    `res_seq`        INT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '방제 순번', 
    `res_pesticide`  VARCHAR(50)     NOT NULL    COMMENT '방제 사용 약품', 
    `res_rsvtime`    DATETIME        NOT NULL    COMMENT '방제 예약 시작시간', 
    `user_id`        VARCHAR(30)     NOT NULL    COMMENT '회원 아이디', 
     PRIMARY KEY (res_seq)
);

ALTER TABLE t_reservation COMMENT '방제 예약 테이블';

ALTER TABLE t_reservation
    ADD CONSTRAINT FK_t_reservation_user_id_t_user_user_id FOREIGN KEY (user_id)
        REFERENCES t_user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- t_monitor Table Create SQL
CREATE TABLE t_monitor
(
    `monit_seq`       NUMERIC(12, 0)    NOT NULL    AUTO_INCREMENT COMMENT '모니터링 순번', 
    `monit_area`      VARCHAR(50)       NOT NULL    COMMENT '모니터링 구역', 
    `monit_time`      DATETIME          NOT NULL    COMMENT '모니터링 시간', 
    `monit_done`      CHAR(1)           NOT NULL    COMMENT '모니터링 완료여부', 
    `monit_infected`  CHAR(1)           NOT NULL    COMMENT '모니터링 전염여부', 
    `user_id`         VARCHAR(30)       NOT NULL    COMMENT '회원 아이디', 
    `res_seq`         INT UNSIGNED      NOT NULL    COMMENT '방제 순번', 
     PRIMARY KEY (monit_seq)
);

ALTER TABLE t_monitor COMMENT '모니터링 테이블';

ALTER TABLE t_monitor
    ADD CONSTRAINT FK_t_monitor_user_id_t_user_user_id FOREIGN KEY (user_id)
        REFERENCES t_user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_monitor
    ADD CONSTRAINT FK_t_monitor_res_seq_t_reservation_res_seq FOREIGN KEY (res_seq)
        REFERENCES t_reservation (res_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- t_environment Table Create SQL
CREATE TABLE t_environment
(
    `evnr_seq`     INT UNSIGNED      NOT NULL    AUTO_INCREMENT COMMENT '환경 순번', 
    `evnr_temprt`  NUMERIC(18, 1)    NOT NULL    COMMENT '환경 온도', 
    `evnr_humid`   NUMERIC(18, 1)    NOT NULL    COMMENT '환경 습도', 
    `evnr_time`    DATETIME          NOT NULL    DEFAULT now() COMMENT '환경 시간', 
    `user_id`      VARCHAR(30)       NOT NULL    COMMENT '회원 아이디', 
     PRIMARY KEY (evnr_seq)
);

ALTER TABLE t_environment COMMENT '환경 테이블';

ALTER TABLE t_environment
    ADD CONSTRAINT FK_t_environment_user_id_t_user_user_id FOREIGN KEY (user_id)
        REFERENCES t_user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- t_farm Table Create SQL
CREATE TABLE t_farm
(
    `farm_seq`      NUMERIC(12, 0)    NOT NULL    AUTO_INCREMENT COMMENT '농장 번호', 
    `farm_plant`    NUMERIC(18, 0)    NOT NULL    COMMENT '농장 주수', 
    `farm_linenum`  NUMERIC(12, 0)    NOT NULL    COMMENT '농장 라인수', 
    `farm_areanum`  NUMERIC(12, 0)    NOT NULL    COMMENT '농장 구역수', 
    `user_id`       VARCHAR(30)       NOT NULL    COMMENT '회원 아이디', 
     PRIMARY KEY (farm_seq)
);

ALTER TABLE t_farm COMMENT '농장 테이블';

ALTER TABLE t_farm
    ADD CONSTRAINT FK_t_farm_user_id_t_user_user_id FOREIGN KEY (user_id)
        REFERENCES t_user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- t_reply Table Create SQL
CREATE TABLE t_reply
(
    `rep_seq`      NUMERIC(12, 0)    NOT NULL    AUTO_INCREMENT COMMENT '댓글 순번', 
    `rep_content`  VARCHAR(4000)     NOT NULL    COMMENT '댓글 내용', 
    `rep_time`     DATETIME          NOT NULL    DEFAULT now() COMMENT '댓글 작성날짜', 
    `uesr_id`      VARCHAR(30)       NOT NULL    COMMENT '유저 아이디', 
    `board_seq`    INT UNSIGNED      NOT NULL    COMMENT '게시글 순번', 
     PRIMARY KEY (rep_seq)
);

ALTER TABLE t_reply COMMENT '댓글 테이블';

ALTER TABLE t_reply
    ADD CONSTRAINT FK_t_reply_board_seq_t_board_board_seq FOREIGN KEY (board_seq)
        REFERENCES t_board (board_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE t_reply
    ADD CONSTRAINT FK_t_reply_uesr_id_t_user_user_id FOREIGN KEY (uesr_id)
        REFERENCES t_user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- t_diary Table Create SQL
CREATE TABLE t_diary
(
    `diary_seq`      INT UNSIGNED    NOT NULL    AUTO_INCREMENT COMMENT '다이어리 순번', 
    `diary_title`    VARCHAR(200)     NOT NULL    COMMENT '다이어리 제목', 
    `diary_content`  VARCHAR(4000)     NOT NULL    COMMENT '다이어리 내용', 
    `diary_dt`       DATETIME     NOT NULL    COMMENT '다이어리 날짜', 
    `diary_id`       VARCHAR(30)     NOT NULL    COMMENT '작성자 아이디', 
     PRIMARY KEY (diary_seq)
);

ALTER TABLE t_diary COMMENT '다이어리 테이블';

ALTER TABLE t_diary
    ADD CONSTRAINT FK_t_diary_diary_id_t_user_user_id FOREIGN KEY (diary_id)
        REFERENCES t_user (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;