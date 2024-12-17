-- User 더미
INSERT INTO user_tb (email, password, nickname, created_at)
VALUES ('a@a', '$2a$10$cp7ofvIT1xXCVRtocaxxM.sHBfUh.y1lR6rZ1ijhoFA.JHEdko2tS', 'test', now());
INSERT INTO user_tb (email, password, nickname, created_at)
VALUES ('b@b', '$2a$10$9DEyZTarJYA5ZH303Mjv4uCwldbrWM/POnvJ30PVaYy8aQ/SxUs9K', 'test2', now());

-- Worldcup 더미
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터1', '월드컵 설명1', 1, 1, 16, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터2', '월드컵 설명2', 1, 1, 2, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터3', '월드컵 설명3', 1, 1, 4, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터4', '월드컵 설명4', 1, 1, 11, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터5', '월드컵 설명5', 1, 1, 13, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터6', '월드컵 설명6', 1, 1, 144, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터7', '월드컵 설명7', 1, 1, 11, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 검색8', '월드컵 설명8', 1, 1, 221, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터9', '월드컵 설명9', 1, 1, 13, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터10', '월드컵 설명10', 1, 2, 11, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터11', '월드컵 설명11', 1, 2, 13, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('월드컵 더미 데이터12', '월드컵 설명12', 1, 2, 21, now());

-- 썸네일 2개 체크를 위한 WorldcupItem
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id, champion_count)
VALUES ('썸네일1', '/img/101.png', 1, 8);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id, champion_count)
VALUES ('썸네일2', '/img/102.png', 1, 6);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id, champion_count)
VALUES ('썸네일3', '/img/103.png', 1, 2);

-- WorldcupGame 더미
INSERT INTO worldcup_game_tb (worldcup_id, user_id, start_round, created_at)
VALUES (1, 1, 8, now());

-- WorldcupItem
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('1', '/img/1.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('2', '/img/2.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('3', '/img/3.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('4', '/img/4.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('5', '/img/5.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('6', '/img/6.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('7', '/img/7.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('8', '/img/8.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('9', '/img/9.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('10', '/img/10.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('11', '/img/11.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('12', '/img/12.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('13', '/img/13.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('14', '/img/14.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('15', '/img/15.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('16', '/img/16.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('17', '/img/17.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('18', '/img/18.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('19', '/img/19.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('20', '/img/20.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('21', '/img/21.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('22', '/img/22.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('23', '/img/23.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('24', '/img/24.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('25', '/img/25.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('26', '/img/26.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('27', '/img/27.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('28', '/img/28.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('29', '/img/29.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('30', '/img/30.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('31', '/img/31.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('32', '/img/32.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('33', '/img/33.png', 1);

--WorldcupMatch 더미
INSERT INTO worldcup_match_tb (match_num, round, worldcup_game_id, worldcup_item1_id, worldcup_item2_id)
VALUES (1, 1, 1,1, 2);

--Comment 더미
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용1', '우승자이름1', '닉네임1', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용2', '우승자이름2', '닉네임2', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용3', '우승자이름3', '닉네임3', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용4', '우승자이름4', '닉네임4', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용5', '우승자이름5', '닉네임5', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용6', '우승자이름6', '닉네임6', 1, 2, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용7', '우승자이름7', '닉네임7', 1, 2, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용8', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용9', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용10', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용11', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용12', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용13', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용14', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용15', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용16', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용17', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용18', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용19', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용20', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용21', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용22', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용23', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용24', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용25', '우승자이름8', '닉네임8', 1, 1, now());
INSERT INTO comment_tb (content, winnername, nickname, user_id, worldcup_id, created_at)
VALUES ('댓글 내용26', '우승자이름8', '닉네임8', 1, 1, now());