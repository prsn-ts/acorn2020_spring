-- 상품 테이블
CREATE TABLE shop(
	num NUMBER PRIMARY KEY, --상품번호
	name VARCHAR2(30), --상품이름
	price NUMBER, --상품가격
	remainCount NUMBER CHECK(remainCount >= 0) --재고갯수 (제약조건에 위배되는 일이 생기면 DataAccessException이 발생함)
);

-- 고객 계좌 테이블
CREATE TABLE client_account(
	id VARCHAR2(30) PRIMARY KEY, -- 고객의 아이디
	money NUMBER CHECK(money >= 0), -- 고객의 잔고 
	point NUMBER
);

-- 주문 테이블
CREATE TABLE client_order(
	num NUMBER PRIMARY KEY, -- 주문번호
	id VARCHAR2(30), -- 주문 고객의 아이디
	code NUMBER, -- 주문한 상품의 번호 
	addr VARCHAR2(50) -- 배송 주소
);

-- 주문 테이블에 사용할 시퀀스 
CREATE SEQUENCE client_order_seq;


-- sample 데이터
INSERT INTO shop (num,name,price,remainCount)
VALUES(1, '사과', 1000, 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(2, '바나나', 2000, 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(3, '귤', 3000, 5);

INSERT INTO client_account (id, money, point) -- 샘플 고객 정보(포인트 적립은 구매 가격의 10%로 계산)
VALUES('superman', 10000, 0);

INSERT INTO client_account (id, money, point) -- 샘플 고객 정보(포인트 적립은 구매 가격의 10%로 계산)
VALUES('batman', 10000, 0);

CREATE TABLE board_cafe(  -- 스마트에디터를 적용한 게시판 글쓰기 및 글 목록 출력 용도의 테이블 
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB,
	viewCount NUMBER, -- 조회수
	regdate DATE
);

CREATE SEQUENCE board_cafe_seq;

/* 댓글 저장하는 테이블 */
CREATE TABLE board_cafe_comment(
	num NUMBER PRIMARY KEY, -- 댓글의 글 번호
	writer VARCHAR2(100), -- 댓글 작성자의 아이디
	content VARCHAR2(500), -- 댓글 내용
	target_id VARCHAR2(100), -- 댓글의 대상자 아이디(누구에게 댓글을 달았는지 확인여부)
	ref_group NUMBER, -- 하나의 글(ex. 글 번호가 80번이라 가정하면)에서 파생된(작성된) 모든 댓글은 80번 글 번호에 관련된 댓글이다 라는 것을 그룹으로 묶어서 명시.(80번이 아닌 글들의 댓글을 가져오면 안되기 때문에 해당 글(여기선 80번)의 댓글을 가져오기위해서 그룹으로 관리하기 위함) 
	comment_group NUMBER, -- 하나의 댓글(ex. 글 번호가 12번이라 가정하면)에 대한 대댓글 들의 글 번호를 12번으로 지정해서 글에 달았던 최초 댓글과 그 아래 대댓글을 하나의 그룹으로 묶어서 같이 표현해야할 때 사용하기위해 사용하는 칼럼(그룹으로 관리하기 위함)
	deleted CHAR(3) DEFAULT 'no', -- 댓글이 삭제된지 여부(삭제된 경우 deleted 칼럼에 'yes' 문자열을 넣는다)
	regdate DATE
);
/* 댓글 관련 시퀀스 */
CREATE SEQUENCE board_cafe_comment_seq;

CREATE TABLE board_gallery(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100),
	caption VARCHAR2(200),
	imagePath VARCHAR2(100),
	regdate DATE
);

CREATE SEQUENCE board_gallery_seq;

-- upload 된 파일의 정보를 저장할 테이블
CREATE TABLE board_file(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	orgFileName VARCHAR2(100) NOT NULL, -- 원본 파일명
	saveFileName VARCHAR2(100) NOT NULL, -- 서버에 실제로 저장된 파일명
	fileSize NUMBER NOT NULL,
	regdate DATE
);
-- 파일명을 다르게 저장 하는 이유는 작성자가 올렸던 파일들의 이름이 겹칠 수 있기 때문에 서버에 저장할 칼럼따로 실제 작성된 파일명 따로 저장해 보관해놓는다.

-- 사용자(회원) 정보를 저장할 테이블
CREATE TABLE users(
	id VARCHAR2(100) PRIMARY KEY,
	pwd VARCHAR2(100) NOT NULL,
	email VARCHAR2(100),
	profile VARCHAR2(100), -- 프로필 이미지 경로를 저장할 칼럼
	regdate DATE -- 가입일 관련
);