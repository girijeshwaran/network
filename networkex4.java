import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class networkex4 {

    public static void main(String[] args) {
        try {
            // Step 1: Get the default gateway IP address using 'ipconfig' command
            String gatewayIP = getDefaultGateway();
            if (gatewayIP != null) {
                System.out.println("Next Hop Router IP Address: " + gatewayIP);

                // Step 2: Get the MAC address of the default gateway using 'arp -a' command
                String macAddress = getMacAddress(gatewayIP);
                if (macAddress != null) {
                    System.out.println("Next Hop Router MAC Address: " + macAddress);
                } else {
                    System.out.println("Could not find MAC address for IP: " + gatewayIP);
                }
            } else {
                System.out.println("Could not find next hop router IP address.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDefaultGateway() {
        try {
            Process process = Runtime.getRuntime().exec("ipconfig");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Use regular expression to find the default gateway IP address
            Pattern pattern = Pattern.compile("Default Gateway[ .]*: ([0-9.]+)");
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    return matcher.group(1); // Return the gateway IP address
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMacAddress(String ip) {
        try {
            Process process = Runtime.getRuntime().exec("arp -a " + ip);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            // Read the output and append it to the StringBuilder
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            Pattern pattern = Pattern.compile(".*" + ip.replace(".", "\\.") + ".*\\s(([0-9A-Fa-f]{1,2}[:-]){5}[0-9A-Fa-f]{1,2}).*");
            Matcher matcher = pattern.matcher(output.toString());

            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
