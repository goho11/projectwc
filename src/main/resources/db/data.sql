-- User 더미
INSERT INTO user_tb (email, password, nickname, created_at)
VALUES ('a@a', '$2a$10$cp7ofvIT1xXCVRtocaxxM.sHBfUh.y1lR6rZ1ijhoFA.JHEdko2tS', 'test', now());
INSERT INTO user_tb (email, password, nickname, created_at)
VALUES ('b@b', '$2a$10$9DEyZTarJYA5ZH303Mjv4uCwldbrWM/POnvJ30PVaYy8aQ/SxUs9K', 'test2', now());

-- Worldcup 더미
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('음식 월드컵', '맛있는 음식을 가려보자', 1, 1, 16, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('원피스 최강자 전', '원피스 캐릭터 중 누가 제일 강한가?', 1, 1, 2, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('남자 아이돌 월드컵', '제작자 취향대로 만든 월드컵', 1, 1, 4, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('철권 적폐캐릭 월드컵', '그 중 최고는 놈징징', 1, 1, 11, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('티니핑 월드컵', '핑!핑!핑!핑!', 1, 1, 13, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('자동차 월드컵', '디자인만 보자. 성능 보지말고', 1, 1, 144, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('[무서운짤 주의] 귀신 월드컵', '귀신 무서웡', 1, 1, 11, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('소리없는 k-pop 월드컵', '이상하다? 소리가 없는데 노래가 들려', 1, 1, 221, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('슈퍼 히어로 영화 월드컵', '개인적으로 좋아하는 영화는', 1, 1, 13, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('좀비 아포칼립스 무기 월드컵', '좀비 세상에서 살아남기 위한 무기', 1, 2, 11, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('스폰지밥 인기 월드컵', '스폰지밥 최애 캐릭터 뽑기', 1, 2, 13, now());
INSERT INTO worldcup_tb (title, description, visibility, user_id, games_completed, created_at)
VALUES ('망한 음식 월드컵', '망했네', 1, 2, 21, now());

-- 썸네일 2개 체크를 위한 WorldcupItem


-- WorldcupGame 더미
INSERT INTO worldcup_game_tb (worldcup_id, user_id, start_round, created_at)
VALUES (1, 1, 8, now());

-- WorldcupItem

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('짜장면', '/img/1.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('탕수육', '/img/2.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('짬뽕', '/img/3.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('스시', '/img/4.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('회', '/img/5.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('비빔밥', '/img/6.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('불고기', '/img/7.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('닭가슴살 샐러드', '/img/8.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('탄탄면', '/img/9.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('카이막', '/img/10.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('스테이크', '/img/11.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('수르스트뢰밍', '/img/12.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('피시앤칩스', '/img/13.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('장어젤리', '/img/14.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('알밥', '/img/15.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('돼지국밥', '/img/16.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('국수', '/img/17.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('밀면', '/img/18.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('냉면', '/img/19.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('돈까스', '/img/20.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('규카츠', '/img/21.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('텐동', '/img/22.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('햄버거', '/img/23.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('치킨', '/img/24.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('피자', '/img/25.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('샌드위치', '/img/26.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('오므라이스', '/img/27.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('김밥', '/img/28.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('순대', '/img/29.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('튀김', '/img/30.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('오향장육', '/img/31.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('취두부', '/img/32.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES ('계란빵', '/img/33.png', 1);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id, champion_count)
VALUES ('붕어빵', '/img/34.png', 1, 8);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id, champion_count)
VALUES ('호빵', '/img/35.png', 1, 6);
INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id, champion_count)
VALUES ('초코파이', '/img/36.png', 1, 2);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('루피', '/img/37.png', 2),
    ('조로', '/img/38.png', 2),
    ('상디', '/img/39.png', 2),
    ('쵸파', '/img/40.png', 2),
    ('나미', '/img/41.png', 2),
    ('로빈', '/img/42.png', 2),
    ('크로커다일', '/img/43.png', 2),
    ('검은수염', '/img/44.png', 2),
    ('흰수염', '/img/45.png', 2),
    ('골 D 로져', '/img/46.png', 2),
    ('에이스', '/img/47.png', 2),
    ('스모커', '/img/48.png', 2),
    ('키자루', '/img/49.png', 2),
    ('아오키지', '/img/50.png', 2),
    ('바제스', '/img/51.png', 2),
    ('아카이누', '/img/52.png', 2),
    ('후지토라', '/img/53.png', 2),
    ('료쿠규', '/img/54.png', 2),
    ('비비', '/img/55.png', 2),
    ('루치', '/img/56.png', 2),
    ('카쿠', '/img/57.png', 2),
    ('미호크', '/img/58.png', 2),
    ('샹크스', '/img/59.png', 2),
    ('버기', '/img/60.png', 2),
    ('에넬', '/img/61.png', 2),
    ('시키', '/img/62.png', 2),
    ('핸콕', '/img/63.png', 2),
    ('카이도', '/img/64.png', 2),
    ('마르코', '/img/65.png', 2);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('지민', '/img/66.png', 3),
    ('태양', '/img/67.png', 3),
    ('정국', '/img/68.png', 3),
    ('민호', '/img/69.png', 3),
    ('카이', '/img/70.png', 3),
    ('지코', '/img/71.png', 3),
    ('우진', '/img/72.png', 3),
    ('원호', '/img/73.png', 3),
    ('민규', '/img/74.png', 3),
    ('도겸', '/img/75.png', 3),
    ('홍빈', '/img/76.png', 3),
    ('유겸', '/img/77.png', 3),
    ('현중', '/img/78.png', 3),
    ('태민', '/img/79.png', 3),
    ('호시', '/img/80.png', 3),
    ('장기용', '/img/81.png', 3);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('진', '/img/82.png', 4),
    ('카즈야', '/img/83.png', 4),
    ('스티브', '/img/84.png', 4),
    ('브라이언', '/img/85.png', 4),
    ('리디아', '/img/86.png', 4),
    ('빅터', '/img/87.png', 4),
    ('리로이', '/img/88.png', 4),
    ('샤오유', '/img/89.png', 4),
    ('레이나', '/img/90.png', 4),
    ('리리', '/img/91.png', 4),
    ('쿠마', '/img/92.png', 4),
    ('헤이하치', '/img/93.png', 4),
    ('리 차오랑', '/img/94.png', 4),
    ('폴 피닉스', '/img/95.png', 4),
    ('아스카', '/img/96.png', 4),
    ('아주세나', '/img/97.png', 4),
    ('드라구노프', '/img/98.png', 4);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('빛나핑', '/img/99.png', 5),
    ('하츄핑', '/img/100.png', 5),
    ('달콤핑', '/img/101.png', 5),
    ('포실핑', '/img/102.png', 5),
    ('초롱핑', '/img/103.png', 5),
    ('눈꽃핑', '/img/104.png', 5),
    ('커핑 머핑', '/img/105.png', 5),
    ('깡총핑', '/img/106.png', 5),
    ('꾸래핑', '/img/107.png', 5),
    ('해핑', '/img/108.png', 5),
    ('시X핑', '/img/109.png', 5),
    ('핑핑이', '/img/110.png', 5),
    ('위험핑', '/img/111.png', 5),
    ('백핑', '/img/112.png', 5),
    ('행운핑', '/img/113.png', 5),
    ('요거핑', '/img/114.png', 5),
    ('빤짝핑', '/img/115.png', 5);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('페라리 라페라리', '/img/116.png', 6),
    ('람보르기니 아벤타도르', '/img/117.png', 6),
    ('부가티 베이론', '/img/118.png', 6),
    ('포르쉐 918 스파이더', '/img/119.png', 6),
    ('맥라렌 P1', '/img/120.png', 6),
    ('아우디 R8', '/img/121.png', 6),
    ('롤스로이스 팬텀', '/img/122.png', 6),
    ('벤틀리 컨티넨탈 GT', '/img/123.png', 6),
    ('재규어 C-X75', '/img/124.png', 6),
    ('시트로엥 SM', '/img/125.png', 6),
    ('코닉세그 아게라 RS', '/img/126.png', 6),
    ('파가니 우아이라', '/img/127.png', 6),
    ('페라리 488 스파이더', '/img/128.png', 6),
    ('람보르기니 우르스', '/img/129.png', 6),
    ('메르세데스-AMG GT', '/img/130.png', 6),
    ('포르쉐 911 터보', '/img/131.png', 6),
    ('마세라티 그란투리스모', '/img/132.png', 6);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('프레디 크루거', '/img/133.png', 7),
    ('페니와이즈', '/img/134.png', 7),
    ('빨간마스크', '/img/135.png', 7),
    ('애나벨', '/img/136.png', 7),
    ('착신아리', '/img/137.png', 7),
    ('머리가 자라는 인형', '/img/138.png', 7),
    ('곤지암 귀신', '/img/139.png', 7),
    ('거울귀신', '/img/140.png', 7),
    ('팔척귀신', '/img/141.png', 7),
    ('봉천동귀신', '/img/142.png', 7),
    ('자유로귀신', '/img/143.png', 7),
    ('물구나무귀신', '/img/144.png', 7),
    ('사다코', '/img/145.png', 7),
    ('팔꿈치귀신', '/img/146.png', 7),
    ('여고괴담', '/img/147.png', 7),
    ('강시', '/img/148.png', 7);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('소녀시대-Gee', '/img/149.png', 8),
    ('원더걸스-텔미', '/img/150.png', 8),
    ('샤이니-링딩동', '/img/151.png', 8),
    ('YB-나는 나비', '/img/152.png', 8),
    ('day6-한 페이지가 될 수 있게', '/img/153.png', 8),
    ('빅뱅-붉은노을', '/img/154.png', 8),
    ('조용필-바운스', '/img/155.png', 8),
    ('조용필-모나리자', '/img/156.png', 8),
    ('쿨-애상', '/img/157.png', 8),
    ('러브홀릭스-Butterfly(국가대표OST)', '/img/158.png', 8),
    ('코요태-우리의 꿈', '/img/159.png', 8),
    ('유정석-질풍가도', '/img/160.png', 8),
    ('소찬휘-tears', '/img/161.png', 8),
    ('박완규-천년의 사랑', '/img/162.png', 8),
    ('izi-응급실', '/img/163.png', 8),
    ('김장훈-허니', '/img/164.png', 8),
    ('강진-땡벌', '/img/165.png', 8);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('어벤져스: 엔드게임', '/img/166.png', 9),
    ('다크 나이트', '/img/167.png', 9),
    ('어벤져스: 인피니티 워', '/img/168.png', 9),
    ('스파이더 맨: 노 웨이 홈', '/img/169.png', 9),
    ('아이언맨', '/img/170.png', 9),
    ('어벤져스', '/img/171.png', 9),
    ('스파이더맨 2', '/img/172.png', 9),
    ('아이언맨 3', '/img/173.png', 9),
    ('로건', '/img/174.png', 9),
    ('스파이더맨', '/img/175.png', 9),
    ('엑스맨: 데이즈 오브 퓨쳐 패스트', '/img/176.png', 9),
    ('캡틴 아메리카: 윈터 솔져', '/img/177.png', 9),
    ('베놈', '/img/178.png', 9),
    ('어메이징 스파이더맨 2', '/img/179.png', 9),
    ('데드풀', '/img/180.png', 9),
    ('블랙 팬서', '/img/181.png', 9),
    ('닥터 스트레인지', '/img/182.png', 9);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('마체테', '/img/183.png', 10),
    ('할버드', '/img/184.png', 10),
    ('야구방망이', '/img/185.png', 10),
    ('쇠지렛대', '/img/186.png', 10),
    ('철퇴', '/img/187.png', 10),
    ('야전삽', '/img/188.png', 10),
    ('양손도끼', '/img/189.png', 10),
    ('채찍', '/img/190.png', 10),
    ('슬랫지해머', '/img/191.png', 10),
    ('삼지창', '/img/192.png', 10),
    ('모던액스', '/img/193.png', 10),
    ('창', '/img/194.png', 10),
    ('레이피어', '/img/195.png', 10),
    ('일본도', '/img/196.png', 10),
    ('괭이', '/img/197.png', 10),
    ('방패', '/img/198.png', 10),
    ('대검', '/img/199.png', 10);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('스폰지밥', '/img/200.png', 11),
    ('뚱이', '/img/201.png', 11),
    ('징징이', '/img/202.png', 11),
    ('다람이', '/img/203.png', 11),
    ('집게사장', '/img/204.png', 11),
    ('진주', '/img/205.png', 11),
    ('플랑크톤', '/img/206.png', 11),
    ('퐁퐁부인', '/img/207.png', 11),
    ('래리', '/img/208.png', 11),
    ('시민1', '/img/209.png', 11),
    ('시민2', '/img/210.png', 11),
    ('시민3', '/img/211.png', 11),
    ('핑핑이', '/img/212.png', 11),
    ('인어맨', '/img/213.png', 11),
    ('조개소년', '/img/214.png', 11),
    ('바다도깨비', '/img/215.png', 11),
    ('캐런', '/img/216.png', 11);

INSERT INTO worldcup_item_tb (itemname, img_url, worldcup_id)
VALUES
    ('계란후라이(펜케이크 아님)', '/img/217.png', 12),
    ('계란후라이?', '/img/218.png', 12),
    ('가래떡구이', '/img/219.png', 12),
    ('된장찌개', '/img/220.png', 12),
    ('딸기탕후루', '/img/221.png', 12),
    ('불맛탕후루', '/img/222.png', 12),
    ('마카롱 메타몽', '/img/223.png', 12),
    ('마카롱', '/img/224.png', 12),
    ('토스트', '/img/225.png', 12),
    ('김치 카츠동', '/img/226.png', 12),
    ('스파게티 직화구이', '/img/227.png', 12),
    ('마시멜로 메테오', '/img/228.png', 12),
    ('지옥의 연어장', '/img/229.png', 12),
    ('카레 연금술', '/img/230.png', 12),
    ('김치찌개(였던것)', '/img/231.png', 12),
    ('라면 불향 첨가', '/img/232.png', 12),
    ('석탄 만두', '/img/233.png', 12),
    ('파워에이드 계란말이', '/img/234.png', 12),
    ('김치만두인 척하는 무언가', '/img/235.png', 12),
    ('김치후레이크', '/img/236.png', 12),
    ('곱랭쿠키', '/img/237.png', 12),
    ('불고기모양 초콜릿', '/img/238.png', 12),
    ('계란찜', '/img/239.png', 12),
    ('가래떡', '/img/240.png', 12),
    ('고구마 맛탕', '/img/241.png', 12),
    ('돼지고기 아니고 쿠키임', '/img/242.png', 12),
    ('행복한 판다쿠키', '/img/243.png', 12),

    ('수플레 돌케이크', '/img/244.png', 12),
    ('벽돌 브라우니', '/img/245.png', 12),
    ('돼지국 파스타', '/img/246.png', 12),
    ('애호박 "양파"볶음', '/img/247.png', 12),
    ('유체이탈', '/img/248.png', 12),
    ('피폭 피자', '/img/249.png', 12),
    ('독이 든 쿠키', '/img/250.png', 12);


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