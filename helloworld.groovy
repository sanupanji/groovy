@Grab('org.yaml:snakeyaml:1.17')
import org.yaml.snakeyaml.*
import org.yaml.snakeyaml.constructor.*
import groovy.transform.*

InputStream input = new FileInputStream(new File("file.yaml"));
Yaml yaml = new Yaml();

a="benefit-ms"
all="api-gateway-ms,abcd-efgh-ms,ijk-lmn-ms,er-qwerfs-ff-ag-ns"
req="fwrfrg-fg-d-g-e-ms,api-gateway-ms,aa-er-e-r-we,ijk-lmn-ms,era--as-fa-f"
arr=all.split(",")
brr=req.split(",")
rest=(arr-brr)
println(rest)
println(arr)

st=a.replaceAll("ms","").replaceAll("-","(.*)")
st="(.*)"+st+"(.*)"
println(st)
Object data = yaml.load(input);
p=data.objects.spec.triggers[0].find { it.imageChange.from.name.matches(st) }
println("=================================================")
println(p)
println("=================================================")
println(data.objects.spec.triggers[0])
println("=================================================")
data.objects.spec.triggers[0].removeElement(p)
println(data.objects.spec.triggers[0])
DumperOptions options = new DumperOptions()
options.setPrettyFlow(true)
options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK)
yamlw = new Yaml(options)
yamlw.dump(data, new FileWriter("file2.yaml"))
    
