-- User 더미
INSERT INTO user_tb (email, password, nickname, created_at)
VALUES ('test', '1234', 'test', now());

-- Worldcup 더미
INSERT INTO worldcup_tb (visibility, user_id, created_at)
VALUES (1, 1, now());

-- WorldcupItem
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('1', '/img/1.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('2', '/img/2.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('3', '/img/3.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('4', '/img/4.png', 1);