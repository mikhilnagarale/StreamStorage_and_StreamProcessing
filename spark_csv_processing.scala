val df = spark.read.format("csv").option("header","true").option("inferSchema","true").option("nullValue","true").option("timestamp",
"yyyy-MM-dd'T'HH:mm:ss").option("path","/user/root/survey.csv").option("mode","failfast").load()

val df1 = df.select($"gender",$"treatment")  

 val df2 = df1.select($"gender",(when($"treatment"==="Yes",1).otherwise(0)).alias("All-Yes"),(when($"treatment"==="No",1).otherwise(0)
).alias("All-No"))


df2.collect

 val df3=df2.groupBy("gender").agg(sum($"All-Yes"),sum($"All-No"))                            
 
 
