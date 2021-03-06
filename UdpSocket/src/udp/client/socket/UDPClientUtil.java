package udp.client.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClientUtil {
	
	private static String ip = IP地址;  //注意 ip地址不要上传git IP地址 
	
	private static int port = 端口;//端口 9999
	
	private static DatagramSocket udpClient;
	
	public static DatagramSocket getUDPClient(){
		if(udpClient == null){
			synchronized (Object.class) {
				if(udpClient == null){
					try {
						udpClient = new DatagramSocket();
					} catch (SocketException e) {
						// TODO Auto-generated catch block
						System.out.println("------DatagramSocket failed------");
						e.printStackTrace();
					}
				}
			}
		}
		return udpClient;
	}
	
	public static void sendPacket(String xml) throws Exception{
		System.out.println("---UDP send packet xml:"+xml);
		byte[] sendBuf = xml.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length,
				InetAddress.getByName(ip), port);
		UDPClientUtil.getUDPClient().send(sendPacket);
	}
	
	public static void main(String[] args) throws Exception {
        String returncode = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg id=\"ANDROID1445248578652^jstj001\" "
        		+ "from=\"jstj001@coship-mes.com/ANDROID\" to=\"8120010155900365@coship-mes.com/ANDROID\" type=\"control\">"
        		+ "<body>{\"Param\":\"{\\\"KeyCode\\\":4}\",\"Cmd\":105,\"Timestamp\":1445248578646,\"Type\":0}</body></msg>";
        
		sendPacket(returncode);
	}
}
