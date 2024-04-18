package com.example.gemini.Model;

import org.springframework.data.repository.CrudRepository;

public interface DataProcReqMentRepository extends CrudRepository<DataProcessReqModel, Integer> {

    // เพิ่มเมธอดเพื่อค้นหา Science Plan ตามเงื่อนไขต่างๆ ตามที่คุณต้องการ
    // เช่น ค้นหาด้วย creator, submitter, หรือ status
    // หรือเพิ่มเมธอดอื่นๆ ตามความต้องการของคุณ
}
