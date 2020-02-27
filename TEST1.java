package tst;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class TEST1 {

	public static void main(String[] args) {
		// Calendarクラスのオブジェクトを生成する
		Calendar cl = Calendar.getInstance();// カレンダーオブジェクトの生成

				/* 課題０の開始日 */
		cl.set(Calendar.YEAR, 2015); // 年
		cl.set(Calendar.MONTH, 0); // 月
		cl.set(Calendar.DATE, 1); // 日

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// 日付の変換
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年M月d日");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf4 = new SimpleDateFormat("E曜日");
		SimpleDateFormat sdf5 = new SimpleDateFormat("dd");
		SimpleDateFormat sdf6 = new SimpleDateFormat("(E)");
		SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy年M月");
		SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf9 = new SimpleDateFormat("M");

		int j = 1; //初日
		int suu = 25;//25日
		int n = 1;

		/**************************************************課題０******************************************************/
		while (j < 366) { // 1年分()３６５日分の表示
			int sum = 0;
			String str = sdf.format(cl.getTime()); //sdf.format(cl.getTime()); が str～4 に格納
			String str2 = sdf2.format(cl.getTime()); //	yyyy年M月d日
			String str3 = sdf3.format(cl.getTime()); //	yyyyMMdd
			String str4 = sdf4.format(cl.getTime()); //	E曜日
			String str5 = sdf5.format(cl.getTime()); //	日にち
			String kyu = String.valueOf(suu);

			String[] ymd = str3.split("", 0); // ymdに文字列として変換

			/***************************************日付の加算***************************************************/
			for (int k = 0; k < 8; k++) {
				int gr = Integer.parseInt(ymd[k]); // 数値grに変換
				sum += gr; // 合計値の加算
			}
			/*************************************25日給料日の表示***********************************************/
			if (str5.equals(kyu)) {
				System.out.println(str + "	" + str2 + "	" + str3 + "	" + sum + "	" + str4 + "	25日は給料日です。");
			} else {
				System.out.println(str + "	" + str2 + "	" + str3 + "	" + sum + "	" + str4);
			}
			cl.add(Calendar.DATE, 1); // １日を加算
			j++;
		}
		/*********************************課題１(8月から360回分の給料日のみ抽出)***************************************/

			/* 課題１の開始日の設定 */
		cl.set(Calendar.YEAR, 2015); // 年
		cl.set(Calendar.MONTH, 7); // 月
		cl.set(Calendar.DATE, 25); // 日
		cl.set(Calendar.DAY_OF_WEEK, 1); // 曜日

		while (n < 361) { //360回分の比較
			String str5 = sdf5.format(cl.getTime()); //	dd
			String str6 = sdf6.format(cl.getTime()); //	(曜日)
			String str7 = sdf7.format(cl.getTime()); //	yyyy年MM月");
			String str8 = sdf8.format(cl.getTime()); //yyyy
			String str9 = sdf9.format(cl.getTime()); //MM

			int num;//第〇曜日
			int yea = Integer.parseInt(str8);//年
			int dat = Integer.parseInt(str5);//日
			String kyu = String.valueOf(suu); //25日をkyuを付与
			if (Objects.equals(str5, kyu)) { // 25日の比較
				String week = "";

				if (Calendar.SUNDAY == cl.get(Calendar.DAY_OF_WEEK)) {//日曜の処理
					dat -= 2;//金曜日にするために２日分の減算
					cl.add(Calendar.DAY_OF_WEEK, -2);//日曜日を金曜日にする
					week = sdf6.format(cl.getTime());//金曜日をweekに入れる

					switch (str9) {//月の判定　祝日判定

					/*　１月　*/
					case "1":
						if (dat == 1) {//元旦
							dat = cl.get(Calendar.DATE);
						} else {
							if (yea >= 2000) {//2000年から適用
								num = ((dat - 1) / 7) + 1;//成人の日
								if ((num == 2) && (cl.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {//現在の曜日の比較
									dat = cl.get(Calendar.DATE);
									cl.add(Calendar.DATE, 2);
								} else if (dat == 15) {//成人の日
									dat = cl.get(Calendar.DATE);
									cl.add(Calendar.DATE, 2);
								}
								cl.add(Calendar.DATE, 2);
							}
						}
						break;

					/*　２月　*/
					case "2":
						if (dat == 11) {//建国記念日
							if (yea >= 1967) {//1967年から適用
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 2);
							}
						}
						break;

					/*　３月　*/
					case "3":
						if (dat == prvDayOfSpringEquinox(yea)) {//春分の日
							dat -= 1;
							week = sdf6.format(cl.getTime());
							cl.add(Calendar.DAY_OF_WEEK, -2);
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DAY_OF_WEEK, 2);
						}
						break;

					/*　４月　*/
					case "4":
						if (dat == 29) {//昭和の日
							if (yea >= 2007) {//2007年から新設
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 2);
							}
						}
						break;

					/*　５月　*/
					case "5":
						if (dat == 3) { //憲法記念日
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DATE, 2);
						} else if (dat == 4) { //みどりの日
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DATE, 2);
						} else if (dat == 5) { //こどもの日
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DATE, 2);
						}
						break;

					/*　６月　
					case "6":
						break;

						6月は祝日がないため使用停止		*/

					/*　７月　*/
					case "7":
						if (yea >= 2003) {//2003年制定
							num = ((dat - 1) / 7) + 1;//海の日
							if ((num == 3) && (cl.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {//第３月曜
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 2);
								cl.add(Calendar.DAY_OF_WEEK, 2);
							}
						}
						break;

					/*　８月　*/
					case "8":
						if (yea >= 2016) {//2016年制定
							if (dat == 11) {//山の日
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 2);
							}
						}
						break;

					/*　９月　*/
					case "9":
						int MAE = prvDayOfAutumnEquinox(yea);
						if (dat == MAE) { //秋分の日
							dat -= 1;
							cl.add(Calendar.DAY_OF_WEEK, -1);
							week = sdf6.format(cl.getTime());
							dat = cl.get(Calendar.DATE);
						} else if (yea >= 2003) {//敬老の日 2003年
							num = ((dat - 1) / 7) + 1;
							if ((num == 3) && (cl.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 2);
							}
						}
						break;

					/*　１０月　*/
					case "10":
						if (yea >= 2000) {
							num = ((dat - 1) / 7) + 1;//体育の日
							if ((num == 2) && (cl.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {//第2月曜
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 2);
								cl.add(Calendar.DAY_OF_WEEK, 2);
							}
						}
						break;

					/*　１１月　*/
					case "11":
						if (dat == 3) {//文化の日
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DATE, 2);
						} else if (dat == 23) {//勤労感謝の日
							dat -= 1;
							dat = cl.get(Calendar.DATE);
						}
						break;
					/*　１２月　
					case "12":
						break;

						12月は祝日がないため使用停止*/
					}
					System.out.println(n + "回目の給料日は" + str7 + dat + "日" + week + "です。");
					cl.add(Calendar.DATE, 2);//日を元に戻す
					cl.add(Calendar.DAY_OF_WEEK, 2);//曜日を元に戻す

				} else if (Calendar.SATURDAY == cl.get(Calendar.DAY_OF_WEEK)) {//土曜の処理
					dat -= 1;
					cl.add(Calendar.DAY_OF_WEEK, -1);
					week = sdf6.format(cl.getTime());
					switch (str9) {//月の判定　祝日判定

					/*　１月　*/
					case "1":
						if (dat == 1) {//元旦
							dat = cl.get(Calendar.DATE);
						} else {
							if (yea >= 2000) {//2000年制定
								num = ((dat - 1) / 7) + 1;//成人の日
								if ((num == 2) && (cl.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {//現在の曜日の比較
									dat = cl.get(Calendar.DATE);
									cl.add(Calendar.DATE, 1);
								} else if (dat == 15) {//成人の日
									dat = cl.get(Calendar.DATE);
									cl.add(Calendar.DATE, 1);
								}
								cl.add(Calendar.DATE, 1);
							}
						}
						break;

					/*　２月　*/
					case "2":
						if (dat == 11) {//建国記念日
							if (yea >= 1967) {//1967年から適用
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 1);
							}
						}
						break;

					/*　３月　*/
					case "3":
						if (dat == prvDayOfSpringEquinox(yea)) {
							dat -= 1;
							week = sdf6.format(cl.getTime());
							cl.add(Calendar.DAY_OF_WEEK, -1);
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DAY_OF_WEEK, 1);
						}
						break;

					/*　４月　*/
					case "4":
						if (dat == 29) {//昭和の日
							if (yea >= 2008) {
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 1);
							}
						}
						break;

					/*　５月　*/
					case "5":
						if (dat == 3) { //憲法記念日
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DATE, 1);
						} else if (dat == 4) { //みどりの日
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DATE, 1);
						} else if (dat == 5) { //こどもの日
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DATE, 1);
						}
						break;

					/*　６月　
					case "6":
						break;

						6月は祝日がないため使用停止		*/

					/*　７月　*/
					case "7":
						if (yea >= 2003) {//2003年制定
							if(yea == 2020) {//2020東京五輪の影響で7月23日が海の日、7月24日がスポーツの日になる
								cl.add(Calendar.DATE, -1);
								cl.add(Calendar.DAY_OF_WEEK, -1);
								week = sdf6.format(cl.getTime());
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 1);
								cl.add(Calendar.DAY_OF_WEEK, 1);
							}
							num = ((dat - 1) / 7) + 1;//海の日
							if ((num == 3) && (cl.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {//第３月曜
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 1);
								cl.add(Calendar.DAY_OF_WEEK, 1);
							}
						}
						break;

					/*　８月　*/
					case "8":
						if (yea >= 2016) {//2016年制定
							if (dat == 11) {//山の日
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 1);
							}
						}
						break;

					/*　９月　*/
					case "9":
						int MAE = prvDayOfAutumnEquinox(yea);
						if (dat == MAE) { //秋分の日
							dat -= 1;
							week = sdf6.format(cl.getTime());
							cl.add(Calendar.DAY_OF_WEEK, -1);
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DAY_OF_WEEK, 1);
						} else if (yea >= 2003) {//敬老の日
							num = ((dat - 1) / 7) + 1;
							if ((num == 3) && (cl.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 2);
							}
						}
						break;

					/*　１０月　*/
					case "10":
						if (yea >= 2000) {
							num = ((dat - 1) / 7) + 1;//スポーツの日
							if ((num == 2) && (cl.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {//第2月曜
								dat = cl.get(Calendar.DATE);
								cl.add(Calendar.DATE, 1);
								cl.add(Calendar.DAY_OF_WEEK, 1);
							}
						}
						break;

					/*　１１月　*/
					case "11":
						if (dat == 3) {//文化の日
							dat = cl.get(Calendar.DATE);
							cl.add(Calendar.DATE, 1);
						} else if (dat == 23) {//勤労感謝の日
							dat -= 1;
							dat = cl.get(Calendar.DATE);
						}
						break;

					/*　１２月　
					case "12":
					break;

					12月は祝日がないため使用停止*/
					}
					System.out.println(n + "回目の給料日は" + str7 + dat + "日" + week + "です。");
					cl.add(Calendar.DATE, 1);//日を元に戻す
					cl.add(Calendar.DAY_OF_WEEK, 1);//曜日を元に戻す

				} else {
					System.out.println(n + "回目の給料日は" + str7 + dat + "日" + str6 + "です。");
				}
				n++;
			}
			cl.add(Calendar.DATE, 1); // １日を加算
		}
	}
	private static int prvDayOfSpringEquinox(int MY) {//春分の日の移動日の計算
		int SpringEquinox_ret;
		if (MY <= 1947) {
			SpringEquinox_ret = 99; //祝日法施行前
		} else {
			if (MY <= 1979) {
				SpringEquinox_ret = (int) (20.8357 +
						(0.242194 * (MY - 1980)) - (int) ((MY - 1983) / 4));
			} else {
				if (MY <= 2099) {
					SpringEquinox_ret = (int) (20.8431 +
							(0.242194 * (MY - 1980)) - (int) ((MY - 1980) / 4));
				} else {
					if (MY <= 2150) {
						SpringEquinox_ret = (int) (21.851 +
								(0.242194 * (MY - 1980)) - (int) ((MY - 1980) / 4));
					} else {
						SpringEquinox_ret = 99; //2151年以降は略算式が無いので不明
					}
				}
			}
		}
		return SpringEquinox_ret;
	}

	private static int prvDayOfAutumnEquinox(int MY) {//秋分の日の移動日の計算
		int AE = 0;
		if (MY <= 1947) {
			AE = 99; //祝日法施行前
		} else if (MY <= 2099) {
			AE = (int) (23.2488 +
					(0.242194 * (MY - 1980)) - (int) ((MY - 1980) / 4));
		}
		return AE;
	}
}
