```
xmlstarlet edit -N ns='http://maven.apache.org/POM/4.0.0' \                                                                ✔  10902  12:04:20
      --move './/ns:project/ns:profiles/ns:profile[ns:id="kafka"]/ns:dependencies' ".//ns:project/ns:dependencies" \
      pom.xml
```

```
xmlstarlet edit -N ns='http://maven.apache.org/POM/4.0.0' \
      --delete './/ns:project/ns:properties/ns:sqs.profile.name' \
      --delete './/ns:project/ns:profiles/ns:profile[ns:id="sqs"]' \
      pom.xml
```