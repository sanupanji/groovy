@Grab('org.yaml:snakeyaml:1.17')
@Grab('com.xlson.groovycsv:groovycsv:1.3')
import org.yaml.snakeyaml.*
import org.yaml.snakeyaml.constructor.*
import groovy.transform.*
import static com.xlson.groovycsv.CsvParser.parseCsv
 
FILE="file.yaml"

InputStream input = new FileInputStream(new File(FILE));
Yaml yaml = new Yaml();
Object data = yaml.load(input);

parentDOM=data.objects.spec.triggers[0]
//Object searchDOM= imageChange.from.name
def searchDOM = { it.imageChange.from.name.matches(st) }
a="admin-ui-ms"
all="api-gateway-ms,benefit-program-service-ms,globaldata-settings-service-ms,validate-email-ms,epp-admin-ui-ms,grgr-dgfd-gdf-ms"
req="api-gateway-ms,epp-admin-ui-ms,ef-f-sd-f-s-ms"
arr=all.split(",")
brr=req.split(",")
rest=(arr-brr)
println(rest)
println(arr)

for (i in rest){
    println(i)

st=i.replaceAll("ms","").replaceAll("service","").replaceAll("-","(.*)")
st="(.*)"+st+"(.*)" as String
println(st)
//pt=parentDOM*.name.matches("admin")
try{
pt=parentDOM.find { it.imageChange.from.name.matches(st) }
} catch (e){
    pt = null
}
println("=================================================")
println(pt)
println("=================================================")
println(parentDOM)
println("=================================================")
parentDOM.removeElement(pt)
println(parentDOM)

}
DumperOptions options = new DumperOptions()
options.setPrettyFlow(true)
options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK)
yamlw = new Yaml(options)
yamlw.dump(data, new FileWriter("file2.yaml"))
    
