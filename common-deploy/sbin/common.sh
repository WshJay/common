#!/bin/bash
#
# 程序启动脚本
#
# chkconfig: - 84 16
# description: Spring Boot启动脚本
[ -f "/etc/rc.d/init.d/functions" ] && . /etc/rc.d/init.d/functions
#[ -z "$JAVA_HOME" -a -x /etc/profile.d/java.sh ] && . /etc/profile.d/java.sh
#环境变量检察
PATH=$PATH
if [ "$JAVA_HOME" != "" ]; then
echo "JAVA环境变量： $JAVA_HOME "
export PATH=${JAVA_HOME}/bin:$PATH
else
echo "JAVA环境变量未设置或设置有误，请检查，尝试直接启动"
#     exit 1
fi
###############################################################主要配置############################################################
# 项目名称，项目日志位置
PROJECT_NAME=common-server
# 项目启动入口
PROJECT_MAIN=org.wsh.common.server.MainService
###################################################################################################################################
# 启动用户
SERVICE_USER=root
# 程序依赖目录
BASE_DIR=$(cd `dirname $0`; pwd)
LIB_PATH="$BASE_DIR/../lib"
export LIB_PATH
# 配置文件目录
ETC_PATH="$BASE_DIR/../etc"
export ETC_PATH

# JAVA路径
SPRINGBOOTAPP_JAVA=${JAVA_HOME}/bin/java

# spring boot log-file
LOG_PATH="$BASE_DIR/../$PROJECT_NAME.log"
PID_PATH="$BASE_DIR/../$PROJECT_NAME.pid"
LOCK_PATH="/var/lock/subsys/$PROJECT_NAME"

RETVAL=0

pid_of_spring_boot() {
    cat ${PID_PATH}
}
command_print(){
    echo "$1"
    sleep 0.01
}

showlogo(){
command_print "                      77                                                                                                777"
command_print "                      744527                                                                                        7725447"
command_print "                      77272244477                                                                              77145522727"
command_print "                      722777777254442777                                                                7772144522777777727"
command_print "                      745777277777777725444227777                                               777722544512777777777772447"
command_print "                       77555212222777777777222251515555122777777                 7777772155515151522727777777772212515527"
command_print "                            7777214445222777777777772722221212515544477   744255555222222777777777777777222144412777"
command_print "                       77            777244044122222777777777777777777    7227777777777777777222255498427777            77"
command_print "                      7495277777               77722444444455152515117    75415251454444444452277                7772225947"
command_print "                      727722252115552777                         7 777     777                           77254551415227722"
command_print "                      772777777722272212554444422777                                        7777224444451222277777777777277"
command_print "                      705222777777777777777777722221544444444940445527    7441254944404444551122227777777777777777777222407"
command_print "                        7722440445511222272777777777777777777777777727    72277777777777777777777777222222115544940442777"
command_print "                                   77777721454555454551515151515211557    74522521255515555454445455152227777"
command_print "                                                         7777777777777     777777777777                                 77"
command_print "                      700990445522777777777                                                          7777777722225449044907"
command_print "                      772777772727222222115545444444454444555152525227    7242521211554544444444444545555122222727777777727"
command_print "                      777777777777777777777777777777777777777777777227    7527727277777777777777777777777777777777777777227"
command_print "                      7904444494455555515222121252121212222222222222177   7152222222222212121222125552115555454544444445047"
command_print "                           77  777777777777777777777777777777777777777     777777777777777777777777777777777777777 7 7
"
command_print "                       777777777777777777777772222777772727277777277772222777277777777727272227272227777777777777777777777"
command_print "                      72272227772227272727772777777777777777777777777777777777777777777777777777777727777727272227222777217"
command_print "                      7777777777777777777777777777777777777777777777777777727777777777777777777777777777777777777777777777"
command_print "                      77777777777727222222525155514545415222222227777777777777272227221245454555555152521222272777777777777"
command_print "                      72222222222777777                                                                  777777722222222517"
command_print "                                                        7777777777777777777777777777777"
command_print "                                 777777772211444551125222222727272727272227272727272222222212514545422277777777"
command_print "                      7727772212122222222777777777777777777777777777777777777777777777777777777777777777222222125112277777"
command_print "                      71277777777777777777777772222222222222252122222222222222222121212122222222727777777777777777777772217"
command_print "                      7777777777777222221222277777777                                      7777777272222222227777777777777"
command_print "                      77777225545277777                            7     7                                7777255551277777"
command_print "                      7491277                777777777221255515152222212121222221251121222277777777                 7772947"
command_print "                                    7772152222222277777777777777777777777777777777777777777777722225122122777"
command_print "                            7721455227777777777777777777272222525212515222221212221222277777777777777777772722455777"
command_print "                       772552277777777777777722225542277777777                   77777777222252522777777777777777221255277"
command_print "                      72277777777777772255522777                                                 77722522227777777777777257"
command_print "                      777777772222222777                                                                 77722222277777777"
command_print "                      77772157777                                                                              77722122727"
command_print "                      74477                                                                                          777547


"
command_print "788444444409128888880444044444427       788884277777    78887   28887       288887777775888888477727772777    78888888888888888888888888888888847"
command_print " 7777777777774888857777777777777      288847788888887   28882  758887    788885772212225088888527222222527    78888277  777777 777777777 77888887"
command_print "752222222277724088822122222222177   78888887     08188888888888888887   0888009122122222488804122212155227    70887               75457    70882"
command_print " 7777777  78888857 7 77777777777          1888888885    7888             718885777777777588804777777777777    78887728888888888888888888887718857"
command_print "788522255408888880888442122250817   7107      78880     488888887         78880445554445088880425544454527    78881         4807  788827   788827"
command_print "   77488888887     788888817         7188888525888    28889775881         28889777777777588805777777777777    708857       4887   78882    28885"
command_print "70888888027   75947    788888047         74888882   5888887   98884       72098080888880888880008888888897    708857     288887   288817   788857"
command_print "58888881     7888887     48888887   78888888887   7888887      288885                   788842                788827 77888882     788827   708847"
command_print "     7488847 758887 7788881         724277         107            587    7888888888888888880888888888888817   78887 7888885      7588807   788857"
command_print "     758885  77888777588887              7     1057    894   2047             7588887   788847  788847        78887  777      788888807    748847"
command_print "788888857    1888807    788888807     48888   7488887 788882  588887     7088888887     288882    788888827   7888877     7777777 7 7    71888887"
command_print "4885      588888885         78887   7888887     28880   28882  788882    788897        75888847      748887   7888888888888888888888888888888887

"
command_print "2888844488882    207      7717          58888887     7227      7277          288888887   48888882       788888887     747      727   708888807"
command_print "    78887      788884     78847      78887   78888   788885    2887       788827        5887   78887  28887   78882   9887    74887  8847   28887"
command_print "     702      282   887   74077     5887        5887  2027888   257      7887           7807    78577080        7884  784      284   507    48857"
command_print "     2847    48880588887   482      7887        1887 748   78884547      2887      580  788888888    088         885  282      7887  588888887"
command_print "    74887  78887777774885 7888277777 7888577 78880   7885    788887       788807  2880  288    7888   288827 728885   788827 78887  7881"
command_print "     727   747        741777248888887   70888857     7727      777           288888027  7527     7807    4888884         1888817     777"
command_print ""
}
start() {
    [ -e "$LOG_PATH" ] && cnt=`wc -l "$LOG_PATH" | awk '{ print $1 }'` || cnt=1
    last_newline=${cnt}
    echo  $"启动 $PROJECT_NAME: "
    showlogo
    PROJECT_CLASSPATH=${ETC_PATH}
    for f in ${LIB_PATH}/*.jar
    do
        PROJECT_CLASSPATH=${PROJECT_CLASSPATH}:${f}
        command_print "加载$f至CLASSPATH;"
    done
    PROJECT_CLASSPATH=${PROJECT_CLASSPATH}:${ETC_PATH}
    export PROJECT_CLASSPATH

    echo "nohup java -cp \"$PROJECT_CLASSPATH\" $PROJECT_MAIN >> \"$LOG_PATH\" 2>&1 &"
    nohup java -cp "$PROJECT_CLASSPATH" ${PROJECT_MAIN}  >> "$LOG_PATH" 2>&1 &
    echo $! > "$BASE_DIR/../$PROJECT_NAME.pid"
    echo ""
    while { pid_of_spring_boot > /dev/null ; } &&
        ! { tail --lines=+${cnt} "$LOG_PATH" | grep -q '[STARTED]' ; } ; do
        newline=`wc -l "$LOG_PATH" | awk '{ print $1 }'`
	    tail --lines=$(($newline-$last_newline)) "$LOG_PATH"
	    last_newline=${newline}
    done
    tail --lines=$((`wc -l "$LOG_PATH" | awk '{ print $1 }'`-$last_newline)) "$LOG_PATH"
    pid_of_spring_boot > /dev/null
    RETVAL=$?
    [ ${RETVAL} = 0 ] && success $"$STRING" || failure $"$STRING"
    echo

    [ ${RETVAL} = 0 ] && touch "$LOCK_PATH"
}

stop() {
    echo -n "停止$PROJECT_NAME: "

    pid=`pid_of_spring_boot`
    [ -n "$pid" ] && kill ${pid}
    RETVAL=$?
    cnt=10
    echo ""
    while [ ${RETVAL} = 0 -a ${cnt} -gt 0 ] &&
        { pid_of_spring_boot > /dev/null ; } ; do
            sleep 1
            echo -n '.'
            ((cnt--))
    done

    [ ${RETVAL} = 0 ] && rm -f "$LOCK_PATH"
    [ ${RETVAL} = 0 ] && success $"$STRING" || failure $"$STRING"
    rm -f "$BASE_DIR/../$PROJECT_NAME.pid"
    echo
}

status() {
    pid=`pid_of_spring_boot`
    if [ -n "$pid" ]; then
        echo "$PROJECT_NAME (pid $pid) 正在运行......"
        return 0
    fi
    if [ -f "$LOCK_PATH" ]; then
        echo $"${base} 程序已死亡，但SYSLOCK仍存在"
        return 2
    fi
    echo "$PROJECT_NAME已停止"
    return 3
}

# See how we were called.
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    status)
        status
        ;;
    restart)
        stop
        start
        ;;
    *)
        echo $"Usage: $0 {start|stop|restart|status}"
        exit 1
esac

exit ${RETVAL}
