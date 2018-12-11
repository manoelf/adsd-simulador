file_name=$1
cpu=$(ps -aux | grep ffmpeg | head -1 | awk '{ print $3 }')

while [[ $cpu == "0.0" ]]; do
    cpu=$(ps -aux | grep ffmpeg | head -1 | awk '{ print $3 }')
done

echo $cpu >>$file_name

while [[ ! $cpu == "0.0" ]]; do
    echo $cpu >>$file_name
    cpu=$(ps -aux | grep ffmpeg | head -1 | awk '{ print $3 }')
done

