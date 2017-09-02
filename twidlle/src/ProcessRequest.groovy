
import freemarker.template.Configuration
import freemarker.template.Template

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

def file = new File("./twidlle_calls.csv")
def jbossPth = "C:/jboss-eap-5.2/jboss-as/bin/"
def cmd
def modelArray = []
def outFile = new File("./jmx_stats.csv").withWriter {out ->
    out.writeLine("HostName" + "," + "Attribute" + "," + "Value") 
    file.eachLine {line,index ->
        try {
            if (index > 1) {
                def executionInfo = line.split(",")
                //println executionInfo.size()
                if (executionInfo.size() == 8) {
                    cmd =  "cmd /c ${jbossPth}twiddle.bat -s ${executionInfo[0]}:${executionInfo[1]} -u ${executionInfo[2]} -p ${executionInfo[3]} \n\
               ${executionInfo[4]} ${executionInfo[5]}:${executionInfo[6].replace("[comma]",",")} ${executionInfo[7]}".execute().text
                    println cmd 
               
                    def cmdArray = cmd.split("=")
                    def model = new Model(host:executionInfo[0],attribute:cmdArray[0], value: cmdArray[1])
                    modelArray << model
                    out.writeLine(executionInfo[0] + "," + cmdArray[0] + "," +  cmdArray[1])
                } else if (executionInfo.size() == 7){
                    cmd =  "cmd /c ${jbossPth}twiddle.bat -s ${executionInfo[0]}:${executionInfo[1]} -u ${executionInfo[2]} -p ${executionInfo[3]} \n\
               ${executionInfo[4]} ${executionInfo[5]}:${executionInfo[6].replace("[comma]",",")}".execute().text
                    println cmd 
                    def cmdArray = cmd.split("=")
                    def model = new Model(host:executionInfo[0],attribute:cmdArray[0], value: cmdArray[0])
                    modelArray << model
                    out.writeLine(executionInfo[0] + "," + cmdArray[0] + "," +  cmdArray[1])
                } else {
                    //            cmd =  "cmd /c ${jbossPth}twiddle.bat -s ${executionInfo[0]}:${executionInfo[1]} -u ${executionInfo[2]} -p ${executionInfo[3]} \n\
                    //               ${executionInfo[4]} ${executionInfo[5]}:${executionInfo[6].replace("[comma]",",")} ${executionInfo[7]} ${executionInfo[8]}".execute().text
                    //            println cmd 
                }
        
            }
        } catch(Exception ex) {
            println ex.message
        }
    }

}

//modelArray.each {println "${it.host}" }

Configuration cfg = new Configuration();
cfg.setDirectoryForTemplateLoading(new File("./config"))
Map<String, Object> input = new HashMap<String, Object>();
input.put("modelArray", modelArray);
Template template = cfg.getTemplate("jms_stats.ftl");
Writer consoleWriter = new OutputStreamWriter(System.out);
template.process(input, consoleWriter);

Writer fileWriter = new FileWriter(new File("output.html"));
try {
    template.process(input, fileWriter);
} finally {
    fileWriter.close();
}

Test.startServer()