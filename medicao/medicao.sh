video_in=$1 

echo "Convertendo para mp3"

./cpu.sh cpu_mp3& (time -p ffmpeg -i $video_in -vn -ab 192k -acodec libmp3lame -ac 2 GOT_.mp3)  2>output_mp3

time=$(cat output_mp3 | grep user)
time=( $time )
echo 'Tempo mp4 para mp3'>>time
echo ${time[1]} >> time

rm output_mp3

echo "Convertendo para avi"

./cpu.sh cpu_avi & (time -p ffmpeg -i $video_in -vcodec copy -acodec copy GOT_.avi) 2> output_avi


time=$(cat output_avi | grep user)
time=( $time )
echo 'Tempo mp4 para avi'>>time
echo ${time[1]} >> time

rm output_avi

