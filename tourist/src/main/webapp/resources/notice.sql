-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.3.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- touristdb 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `touristdb` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci */;
USE `touristdb`;

-- 테이블 touristdb.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `tno` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `view` int(11) NOT NULL DEFAULT 0,
  `createDate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`tno`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 touristdb.notice:~11 rows (대략적) 내보내기
DELETE FROM `notice`;
INSERT INTO `notice` (`tno`, `title`, `content`, `view`, `createDate`) VALUES
	(1, '공지사항1', '공지사항 내용1', 0, '2024-10-17 16:27:19'),
	(2, '이번 여름 휴가 제주 갈까? 미션 투어 (여행경비 50만원 지원)', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.<br>박물관 미션투어에 관심과 참여 감사드립니다. <br>선정되신 분들도 진심으로 축하드립니다. <br>앞으로도 큰 관심 부탁드리며, 메일로도 안내 예정이니 참고하시기 바랍니다. <br>감사합니다. <br><br>[당첨자]<br>김용* kimyong***@naver.com <br>인봉* in2018a***@naver.com<br>예경* yyhong***@naver.com<br>한진* haha***@naver.com<br>박수* pky**@naver.com<br>명진* mma5**@nate.com<br>김영* rtfg6*@naver.com<br>서영* seo20**@gmail.com<br>윤소* yoon2***@naver.com<br>지은* ji***@daum.net', 1, '2024-01-01 16:37:31'),
	(3, '박물관 미션 투어 응모 당첨자 발표', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.<br>박물관 미션투어에 관심과 참여 감사드립니다. <br>선정되신 분들도 진심으로 축하드립니다. <br>앞으로도 큰 관심 부탁드리며, 메일로도 안내 예정이니 참고하시기 바랍니다. <br>감사합니다. <br><br>[당첨자]<br>김용* kimyong***@naver.com <br>인봉* in2018a***@naver.com<br>예경* yyhong***@naver.com<br>한진* haha***@naver.com<br>박수* pky**@naver.com<br>명진* mma5**@nate.com<br>김영* rtfg6*@naver.com<br>서영* seo20**@gmail.com<br>윤소* yoon2***@naver.com<br>지은* ji***@daum.net', 2, '2024-02-02 15:37:31'),
	(4, '이번 여름 휴가 제주 갈까? 미션 투어 (여행경비 50만원 지원)', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.<br>박물관 미션투어에 관심과 참여 감사드립니다. <br>선정되신 분들도 진심으로 축하드립니다. <br>앞으로도 큰 관심 부탁드리며, 메일로도 안내 예정이니 참고하시기 바랍니다. <br>감사합니다. <br><br>[당첨자]<br>김용* kimyong***@naver.com <br>인봉* in2018a***@naver.com<br>예경* yyhong***@naver.com<br>한진* haha***@naver.com<br>박수* pky**@naver.com<br>명진* mma5**@nate.com<br>김영* rtfg6*@naver.com<br>서영* seo20**@gmail.com<br>윤소* yoon2***@naver.com<br>지은* ji***@daum.net', 3, '2024-03-03 16:37:31'),
	(5, '추석 연휴 티켓/투어 배송 및 직접 수령 안내', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.<br>박물관 미션투어에 관심과 참여 감사드립니다. <br>선정되신 분들도 진심으로 축하드립니다. <br>앞으로도 큰 관심 부탁드리며, 메일로도 안내 예정이니 참고하시기 바랍니다. <br>감사합니다. <br><br>[당첨자]<br>김용* kimyong***@naver.com <br>인봉* in2018a***@naver.com<br>예경* yyhong***@naver.com<br>한진* haha***@naver.com<br>박수* pky**@naver.com<br>명진* mma5**@nate.com<br>김영* rtfg6*@naver.com<br>서영* seo20**@gmail.com<br>윤소* yoon2***@naver.com<br>지은* ji***@daum.net', 4, '2024-04-04 16:37:31'),
	(6, '하롱베이 서비스 OPEN! (여행정보, 가이드북, 가이드맵)', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.<br>박물관 미션투어에 관심과 참여 감사드립니다. <br>선정되신 분들도 진심으로 축하드립니다. <br>앞으로도 큰 관심 부탁드리며, 메일로도 안내 예정이니 참고하시기 바랍니다. <br>감사합니다. <br><br>[당첨자]<br>김용* kimyong***@naver.com <br>인봉* in2018a***@naver.com<br>예경* yyhong***@naver.com<br>한진* haha***@naver.com<br>박수* pky**@naver.com<br>명진* mma5**@nate.com<br>김영* rtfg6*@naver.com<br>서영* seo20**@gmail.com<br>윤소* yoon2***@naver.com<br>지은* ji***@daum.net', 5, '2024-05-05 16:37:31'),
	(7, '투어리스트인투어 서비스 점검 안내 - 투어리스트인투어에서 매월 실시하는 정기점검 안내', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.', 6, '2024-06-06 16:37:31'),
	(8, '이번 여름 휴가 제주 갈까? 미션 투어 (여행경비 50만원 지원)', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.<br>박물관 미션투어에 관심과 참여 감사드립니다. <br>선정되신 분들도 진심으로 축하드립니다. <br>앞으로도 큰 관심 부탁드리며, 메일로도 안내 예정이니 참고하시기 바랍니다. <br>감사합니다. <br><br>[당첨자]<br>김용* kimyong***@naver.com <br>인봉* in2018a***@naver.com<br>예경* yyhong***@naver.com<br>한진* haha***@naver.com<br>박수* pky**@naver.com<br>명진* mma5**@nate.com<br>김영* rtfg6*@naver.com<br>서영* seo20**@gmail.com<br>윤소* yoon2***@naver.com<br>지은* ji***@daum.net', 7, '2024-07-07 16:37:31'),
	(9, '박물관 미션 투어 응모 당첨자 발표', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.<br>박물관 미션투어에 관심과 참여 감사드립니다. <br>선정되신 분들도 진심으로 축하드립니다. <br>앞으로도 큰 관심 부탁드리며, 메일로도 안내 예정이니 참고하시기 바랍니다. <br>감사합니다. <br><br>[당첨자]<br>김용* kimyong***@naver.com <br>인봉* in2018a***@naver.com<br>예경* yyhong***@naver.com<br>한진* haha***@naver.com<br>박수* pky**@naver.com<br>명진* mma5**@nate.com<br>김영* rtfg6*@naver.com<br>서영* seo20**@gmail.com<br>윤소* yoon2***@naver.com<br>지은* ji***@daum.net', 8, '2024-08-08 16:37:31'),
	(10, '추석 연휴 티켓/투어 배송 및 직접 수령 안내', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.<br>박물관 미션투어에 관심과 참여 감사드립니다. <br>선정되신 분들도 진심으로 축하드립니다. <br>앞으로도 큰 관심 부탁드리며, 메일로도 안내 예정이니 참고하시기 바랍니다. <br>감사합니다. <br><br>[당첨자]<br>김용* kimyong***@naver.com <br>인봉* in2018a***@naver.com<br>예경* yyhong***@naver.com<br>한진* haha***@naver.com<br>박수* pky**@naver.com<br>명진* mma5**@nate.com<br>김영* rtfg6*@naver.com<br>서영* seo20**@gmail.com<br>윤소* yoon2***@naver.com<br>지은* ji***@daum.net', 9, '2024-09-09 16:37:31'),
	(11, '하롱베이 서비스 OPEN! (여행정보, 가이드북, 가이드맵)', '안녕하세요. 믿을 수 있는 여행정보, 투어리스트인투어입니다.<br>박물관 미션투어에 관심과 참여 감사드립니다. <br>선정되신 분들도 진심으로 축하드립니다. <br>앞으로도 큰 관심 부탁드리며, 메일로도 안내 예정이니 참고하시기 바랍니다. <br>감사합니다. <br><br>[당첨자]<br>김용* kimyong***@naver.com <br>인봉* in2018a***@naver.com<br>예경* yyhong***@naver.com<br>한진* haha***@naver.com<br>박수* pky**@naver.com<br>명진* mma5**@nate.com<br>김영* rtfg6*@naver.com<br>서영* seo20**@gmail.com<br>윤소* yoon2***@naver.com<br>지은* ji***@daum.net', 10, '2024-10-10 16:37:31');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
