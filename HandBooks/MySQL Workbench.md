### 外键
```bash
# 在 workbench 中 选中 sqljoin.topiccomment 表
# 右键 -> Alter Table -> Foreign Keys
1. Foreign Key Name 			
		* FK_topicId_Topic_id
2. Referenced Table 			
		* `ssm`.`topic`
3. Column 								
		* topicId (topiccomment 表的 topicId)
4. Referenced Column 			
		* id (topic 表的 id, 必须是主键)
5. On Update 							
		* CASCADE (级联更新)
6. On Delete 							
		* NO ACTION (级联删除一般不启用)
```
