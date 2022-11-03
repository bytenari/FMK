# Super Bowl 마케팅 이벤트 페이지 
Task to apply for a vacancy in FOCUSMEDIAKOREA

AWS에 서비스를 배포하기 실행하기 위한 절차 

1. AWS의 EC2, S3, RDS를 이용해 서버, 정적 페이지, 데이터베이스를 분리한다.
   - 이 서비스의 서버는 Linux OS 서버에서 구동되고 있음.
   - 데이터베이스는 MySQL를 사용함.
2. EC2에 여러 대의 인스턴스를 만들어 클라이언트와 서버도 분리한다. 이렇게 하면 웹, iOS, Android 등 여러 플랫폼을 다루기에도 좋다.
3. 필요한 만큼 인스턴스를 늘리고 로드밸런서(AWS의 ELB)를 추가하여 트래픽을 처리하도록 한다.
